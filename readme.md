# Currency converter

Currency converter is a spring application for currency conversion.

## Installation

Execute mvn install.

Go to the project folder.

Build docker image with docker.

```bash
docker build -t CurrencyConverter .
```

Run container at port 9090.

```bash
docker run -p 9090:8080 CurrencyConverter
```