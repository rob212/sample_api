from flask import Flask
from flask import Response

from app.lamp import get_lamp

app = Flask(__name__)


@app.route('/', methods=['GET'])
def home():
    return "<h1>Hello World</h1><p>I am a very simple Flask app.</p>"


@app.route('/api')
def api():

    lamp = get_lamp()
    return Response(lamp, status=200, mimetype='application/json')


# # If we're running in stand alone mode, run the application
if __name__ == '__main__':
    app.run(host='0.0.0.0')
