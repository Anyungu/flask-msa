Kind = "ingress-gateway"
Name = "us-east-ingress"


Listeners = [
  {
    Port     = 8080
    Protocol = "http"
    Services = [
      {
        Name = "*"
      }
    ]
  }
]
