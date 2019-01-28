FROM ubuntu:16.04

MAINTAINER Rob McBryde

RUN apt-get update -y && \
    apt-get install -y python-pip python-dev

# We copy the requirements.txt this way to leverage Docker cache
COPY ../../requirements.txt /app/requirements.txt

WORKDIR /app

RUN pip install -r requirements.txt

COPY ../../ /app

ENTRYPOINT [ "python" ]

CMD [ "app.py" ]