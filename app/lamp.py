import json


def get_lamp():
    data = {
        'hello': 'world',
        'number': 5
    }
    return json.dumps(data)
