from datetime import datetime

from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.test


@app.route('/')
def index():
    return render_template('index.html')

@app.route('/detail/<idx>')
def detail(idx):
    post = db.test.find_one({'idx': int(idx)}, {'_id': False})
    return render_template("detail.html", post=post)

@app.route('/post', methods=['POST'])
def save_post():
    title_receive = request.form["title_give"]
    pw_receive = request.form["pw_give"]
    content_receive = request.form["content_give"]
    post_count = db.test.estimated_document_count({}) #db의 도큐먼트의 수를 세어준다.

    if post_count == 0:
        max_value = 1
    else:
        max_value = max_value = db.test.find_one(sort=[("idx", -1)])['idx'] + 1

    doc = {
        'idx': max_value,
        'title': title_receive,
        'content': content_receive,
        'pw': pw_receive,
        'reg_date': datetime.now()
    }

    db.test.insert_one(doc)
    return {"result": "success"}


@app.route('/post', methods=['GET'])
def get_post():
    posts = list(db.test.find({}, {'_id': False}).sort([("reg_date", -1)]))
    for a in posts:
        a['reg_date'] = a['reg_date'].strftime('%Y.%m.%d %H:%M:%S')
    return jsonify({"posts": posts})


@app.route('/post', methods=['DELETE'])
def delete_post():
    idx = request.args.get('idx')
    db.test.delete_one({'idx': int(idx)})
    return {"result": "success"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)