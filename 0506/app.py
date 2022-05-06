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
    article = db.test.find_one({'idx': int(idx)}, {'_id': False})
    return render_template("detail.html", article=article)

@app.route('/articleList', methods=['GET'])
def get_article_list():
    order_receive = request.args.get("order")
    article_list = article_list = list(db.test.find({}, {'_id': False}).sort([("reg_date", -1)]))
    if(order_receive=="asc"):
        article_list = list(db.test.find({}, {'_id': False}).sort([("read_count", -1)]))
    elif(order_receive=="desc") :
        article_list = list(db.test.find({}, {'_id': False}).sort([("read_count", 1)]))

    for article in article_list:
        article['reg_date'] = article['reg_date'].strftime('%Y.%m.%d %H:%M:%S')

    return jsonify({"article_list": article_list})

# Create
@app.route('/article', methods=['POST'])
def create_article():
    title_receive = request.form["title"]
    pw_receive = request.form["pw"]
    content_receive = request.form["content"]
    article_count = db.test.estimated_document_count({})  # db의 도큐먼트의 수를 세어준다.

    if article_count == 0:
        max_value = 1
    else:
        max_value = max_value = db.test.find_one(sort=[("idx", -1)])['idx'] + 1

    doc = {
        'idx': max_value,
        'title': title_receive,
        'content': content_receive,
        'pw': pw_receive,
        'read_count' : 0,
        'reg_date': datetime.now()
    }

    db.test.insert_one(doc)
    return {"result": "success"}

# Read
@app.route('/article', methods=['GET'])
def read_article():
    articles = list(db.test.find({}, {'_id': False}).sort([("reg_date", -1)]))
    for a in articles:
        a['reg_date'] = a['reg_date'].strftime('%Y.%m.%d %H:%M:%S')
    return jsonify({"articles": articles})

# # Update
# @app.route('/article', methods=['PUT'])
# def update_article():
#     idx = request.args.get("idx")
#     val = db.test.find_one({'idx': int(idx)})["read_count"];
#     db.test.update_one({'idx': int(idx)},{'$set':{'read_count': int(val)+1}})
#     return {"result": "success"}

# Update
@app.route('/article', methods=['PUT'])
def update_article():
    idx =  request.args.get('idx')
    title_receive = request.form('title_give')
    content_receive = request.form('content_give')
    db.test.update_one({'idx': int(idx)},{'$set':{'title': title_receive}})
    return {"result": "success"}

# Delete
@app.route('/article', methods=['DELETE'])
def delete_article():
    # todo
    return {"result": "success"}

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8000, debug=True)