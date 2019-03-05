import json


def get_lamp():
    data = {
        'hello': 'world',
        'number': 5
    }
    return _jsonify(data)


def _jsonify(data):
    return json.dumps(data)
