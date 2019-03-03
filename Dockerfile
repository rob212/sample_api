FROM python:3.7-alpine

MAINTAINER Rob McBryde

# We copy the requirements.txt this way to leverage Docker cache
COPY ./requirements.txt /app/requirements.txt

WORKDIR /app

RUN pip install -r requirements.txt

COPY . /app

ENTRYPOINT [ "python" ]

CMD [ "app.py" ]