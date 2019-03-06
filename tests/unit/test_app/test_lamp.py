from app.lamp import get_lamp


def test_get_lamp():
    expected = '{"hello": "world", "number": 5}'
    actual = get_lamp()
    assert expected == actual
