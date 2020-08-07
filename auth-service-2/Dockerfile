



FROM python:3.6.1-alpine
WORKDIR /project
ADD . /project
RUN pip install --upgrade pip
RUN apk update \
    && apk add --virtual build-dependencies \
    build-base \
    gcc \
    libffi-dev
RUN pip install -r requirements.txt
CMD ["python","manage.py","run"]


