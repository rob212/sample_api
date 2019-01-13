import flask
import json

from flask import Response

app = flask.Flask(__name__)


@app.route('/', methods=['GET'])
def home():
    return "<h1>Hello World</h1><p>I am a very simple Flask app.</p>"


@app.route('/api')
def api():

    data = {
        'hello': 'world',
        'number': 5
    }
    js = json.dumps(data)

    return Response(js, status=200, mimetype='application/json')


# If we're running in stand alone mode, run the application
if __name__ == '__main__':
    app.run(host='0.0.0.0')
