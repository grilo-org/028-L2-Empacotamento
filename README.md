
# 📦 Sistema de Empacotamento de Pedidos - Seu Manoel

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Maven
- Swagger (OpenAPI)
- Docker
- JUnit (Testes unitários opcionais)

---

## 📦 Caixas Disponíveis

| Caixa   | Altura (cm) | Largura (cm)  | Comprimento (cm) |
|-------- |-------------|---------------|------------------|
| Caixa 1 | 30          | 40            | 80               |
| Caixa 2 | 80          | 50            | 40               |
| Caixa 3 | 50          | 80            | 60               |

---

## 🔗 Documentação da API

Após subir a aplicação, acesse a documentação interativa via Swagger:

http://localhost:8080/swagger-ui.html

---

## 🏗️ Como Executar o Projeto

### ✅ Pré-requisitos

- Docker instalado na máquina
- Git instalado

---

### 🔥 Executar com Docker

1. **Clone o repositório:**

```bash
git clone https://github.com/GusGD/Empacotamento
cd Empacotamento
```

2. **Build e execução com Docker:**

```bash
docker build -t empacotamento-api .
docker run -p 8080:8080 empacotamento-api
```

A API estará rodando em:

http://localhost:8080/swagger-ui/index.html

---

### 🚀 Executar localmente sem Docker (opcional)

1. **Clone o repositório:**

```bash
git clone https://github.com/GusGD/Empacotamento
cd Empacotamento
```

2. **Execute o projeto com Maven:**

```bash
./mvnw spring-boot:run
```

ou, se tiver Maven instalado:

```bash
mvn spring-boot:run
```

---

## 📥 Exemplo de Requisição (Entrada)

### Endpoint:

POST /api/packing

### Payload:

```json
{
  "pedidos": [
    {
      "pedido_id": 1,
      "produtos": [
        {"produto_id": "PS5", "dimensoes": {"altura": 40, "largura": 10, "comprimento": 25}},
        {"produto_id": "Volante", "dimensoes": {"altura": 40, "largura": 30, "comprimento": 30}}
      ]
    },
    {
      "pedido_id": 2,
      "produtos": [
        {"produto_id": "Joystick", "dimensoes": {"altura": 15, "largura": 20, "comprimento": 10}},
        {"produto_id": "Fifa 24", "dimensoes": {"altura": 10, "largura": 30, "comprimento": 10}},
        {"produto_id": "Call of Duty", "dimensoes": {"altura": 30, "largura": 15, "comprimento": 10}}
      ]
    },
    {
      "pedido_id": 3,
      "produtos": [
        {"produto_id": "Headset", "dimensoes": {"altura": 25, "largura": 15, "comprimento": 20}}
      ]
    },
    {
      "pedido_id": 4,
      "produtos": [
        {"produto_id": "Mouse Gamer", "dimensoes": {"altura": 5, "largura": 8, "comprimento": 12}},
        {"produto_id": "Teclado Mecânico", "dimensoes": {"altura": 4, "largura": 45, "comprimento": 15}}
      ]
    },
    {
      "pedido_id": 5,
      "produtos": [
        {"produto_id": "Cadeira Gamer", "dimensoes": {"altura": 120, "largura": 60, "comprimento": 70}}
      ]
    },
    {
      "pedido_id": 6,
      "produtos": [
        {"produto_id": "Webcam", "dimensoes": {"altura": 7, "largura": 10, "comprimento": 5}},
        {"produto_id": "Microfone", "dimensoes": {"altura": 25, "largura": 10, "comprimento": 10}},
        {"produto_id": "Monitor", "dimensoes": {"altura": 50, "largura": 60, "comprimento": 20}},
        {"produto_id": "Notebook", "dimensoes": {"altura": 2, "largura": 35, "comprimento": 25}}
      ]
    },
    {
      "pedido_id": 7,
      "produtos": [
        {"produto_id": "Jogo de Cabos", "dimensoes": {"altura": 5, "largura": 15, "comprimento": 10}}
      ]
    },
    {
      "pedido_id": 8,
      "produtos": [
        {"produto_id": "Controle Xbox", "dimensoes": {"altura": 10, "largura": 15, "comprimento": 10}},
        {"produto_id": "Carregador", "dimensoes": {"altura": 3, "largura": 8, "comprimento": 8}}
      ]
    },
    {
      "pedido_id": 9,
      "produtos": [
        {"produto_id": "Tablet", "dimensoes": {"altura": 1, "largura": 25, "comprimento": 17}}
      ]
    },
    {
      "pedido_id": 10,
      "produtos": [
        {"produto_id": "HD Externo", "dimensoes": {"altura": 2, "largura": 8, "comprimento": 12}},
        {"produto_id": "Pendrive", "dimensoes": {"altura": 1, "largura": 2, "comprimento": 5}}
      ]
    }
  ]
}
```

---

## 📤 Exemplo de Resposta (Saída)

```json
{
  "pedidos": [
    {
      "pedido_id": 1,
      "caixas": [
        {
          "caixa_id": "Caixa 2",
          "produtos": ["PS5", "Volante"]
        }
      ]
    },
    {
      "pedido_id": 2,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Joystick", "Fifa 24", "Call of Duty"]
        }
      ]
    },
    {
      "pedido_id": 3,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Headset"]
        }
      ]
    },
    {
      "pedido_id": 4,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Mouse Gamer", "Teclado Mecânico"]
        }
      ]
    },
    {
      "pedido_id": 5,
      "caixas": [
        {
          "caixa_id": null,
          "produtos": ["Cadeira Gamer"],
          "observacao": "Produto não cabe em nenhuma caixa disponível."
        }
      ]
    },
    {
      "pedido_id": 6,
      "caixas": [
        {
          "caixa_id": "Caixa 3",
          "produtos": ["Monitor", "Notebook"]
        },
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Webcam", "Microfone"]
        }
      ]
    },
    {
      "pedido_id": 7,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Jogo de Cabos"]
        }
      ]
    },
    {
      "pedido_id": 8,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Controle Xbox", "Carregador"]
        }
      ]
    },
    {
      "pedido_id": 9,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Tablet"]
        }
      ]
    },
    {
      "pedido_id": 10,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["HD Externo", "Pendrive"]
        }
      ]
    }
  ]
}
```

---

## 🛠️ Funcionalidades

- ✅ Receber uma lista de pedidos com produtos e suas dimensões
- ✅ Calcular automaticamente a quantidade e os tipos de caixas necessárias
- ✅ Responder quais produtos estão em cada caixa
- ✅ Documentação Swagger
- ✅ Docker Ready
- 🔐 Autenticação via Spring Security
- ✅  Testes unitários com cobertura das regras de empacotamento

---