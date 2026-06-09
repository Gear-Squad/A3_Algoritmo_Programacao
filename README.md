# 📦 Gear Squad – Sistema de Controle de Estoque

Sistema de controle de estoque desenvolvido em Java para execução via terminal (console), criado como projeto acadêmico da disciplina de **Algoritmos e Programação** da **FASEH – Faculdade da Saúde e Ecologia Humana**.

O projeto foi desenvolvido com o objetivo de aplicar na prática os conceitos fundamentais da **Programação Orientada a Objetos (POO)**, estruturas de decisão, estruturas de repetição, manipulação de coleções e organização modular de software.

---

## 🎯 Objetivo do Projeto

Desenvolver um sistema de controle de estoque capaz de realizar o gerenciamento de produtos através de um menu interativo em terminal, permitindo:

- Cadastro de produtos;
- Movimentação de estoque (entrada e saída);
- Busca de produtos;
- Edição de informações;
- Exclusão de registros;
- Limpeza completa do estoque;
- Listagem organizada dos produtos cadastrados.

Além da implementação funcional, o projeto teve como finalidade reforçar conceitos de lógica de programação, POO, modularização e desenvolvimento colaborativo utilizando Git e GitHub.

---

## 🏗 Estrutura do Projeto

```text
src/
├── Main.java
└── Classes/
    ├── Produto.java
    └── EstoqueService.java
```

### Main.java
Responsável pela interface do sistema e navegação entre menus.

Funções:
- Exibição dos menus;
- Controle de navegação;
- Tratamento de opções inválidas;
- Chamada dos métodos da classe EstoqueService.

### Produto.java
Classe responsável pela modelagem dos produtos cadastrados.

Atributos:
- Código;
- Descrição;
- Quantidade;
- Centro de Custo.

Recursos:
- Encapsulamento;
- Construtores;
- Getters e Setters;
- Método `toString()`.

### EstoqueService.java
Classe responsável pelas regras de negócio do sistema.

Funções:
- Cadastro;
- Movimentação;
- Busca;
- Listagem;
- Edição;
- Exclusão;
- Ordenação dos produtos;
- Validações.

---

## ⚙ Funcionalidades

### 📋 Cadastro de Produtos

Permite cadastrar produtos contendo:

- Código;
- Descrição;
- Quantidade;
- Centro de Custo.

Validações:
- Não permite códigos duplicados;
- Não permite códigos negativos;
- Ordenação automática após cadastro.

---

### 📥 Entrada de Estoque

Permite adicionar quantidades a produtos já cadastrados.

Validações:
- Produto deve existir;
- Quantidade deve ser maior que zero.

---

### 📤 Saída de Estoque

Permite registrar retiradas de materiais.

Validações:
- Produto deve existir;
- Quantidade deve ser maior que zero;
- Não permite retirada superior ao estoque disponível.

---

### ✏ Edição de Produtos

Permite alterar:

- Código;
- Descrição;
- Quantidade;
- Centro de Custo.

Validações:
- Não permite duplicidade de códigos;
- Verifica existência do produto antes da edição.

---

### 🔎 Busca de Produtos

Consulta individual através do código do produto.

---

### 📊 Listagem de Produtos

Exibe todos os produtos cadastrados em formato de tabela organizada.

---

### 🗑 Exclusão de Produtos

Remove produtos individualmente mediante confirmação do usuário.

---

### ♻ Limpeza da Lista

Remove todos os produtos cadastrados mediante confirmação.

---

## 🧠 Conceitos Aplicados

Durante o desenvolvimento foram utilizados conceitos de:

- Programação Orientada a Objetos (POO);
- Encapsulamento;
- Modularização;
- Separação de Responsabilidades;
- Reutilização de Código;
- ArrayList;
- Métodos Auxiliares;
- Estruturas Condicionais;
- Estruturas de Repetição;
- Tratamento de Entradas Inválidas;
- Ordenação de Dados (Bubble Sort).

---

## 🛠 Tecnologias Utilizadas

- Java
- JDK 26
- IntelliJ IDEA
- Git
- GitHub

---

## 👨‍💻 Integrantes

- Bruno Gonçalves de Jesus
- Carlos Eduardo
- Deivisson Jean Lopes
- Kaique Helbert
- Lorrane Ribeiro Miranda dos Santos

---

## 🎓 Informações Acadêmicas

**Instituição:** FASEH – Faculdade da Saúde e Ecologia Humana

**Cursos:**
- Análise e Desenvolvimento de Sistemas
- Ciência da Computação

**Disciplina:** Algoritmos e Programação

**Professores:**
- Cleber Leão (Prática)
- Clayton Barbosa (Teórica)

**Cidade:** Vespasiano – MG

**Período:** 1º Semestre de 2026

---

## 🚀 Fluxo do Sistema

```text
INÍCIO
   │
   ▼
MENU PRINCIPAL
   │
   ├── 1. Cadastro
   │
   ├── 2. Movimentação
   │      ├── Entrada
   │      └── Saída
   │
   ├── 3. Listagem
   │
   ├── 4. Edição
   │
   ├── 5. Exclusão
   │
   ├── 6. Limpar Lista
   │
   └── 0. Sair
   │
   ▼
FIM
```

---

## 📚 Finalidade Acadêmica

Este projeto foi desenvolvido exclusivamente para fins acadêmicos, visando consolidar os conhecimentos adquiridos na disciplina de Algoritmos e Programação, aplicando conceitos de lógica computacional e desenvolvimento orientado a objetos em Java.

---
⭐ Projeto desenvolvido pela equipe **Gear Squad**.
