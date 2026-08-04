# Exemplo Prático: Análise de Código e Qualidade de Software

Este repositório contém um exemplo didático em Java desenvolvido para a disciplina de **Design Patterns**.

O objetivo deste projeto é servir de base para o estudo prático de manutenibilidade, legibilidade e boas práticas de arquitetura e código.

---

## 🎯 Objetivo da Atividade

A classe `UsuarioPrinter` implementa uma funcionalidade completa e operacional: ela recebe uma lista de usuários e renderiza uma tabela formatada no console.

Apesar de o programa funcionar perfeitamente do ponto de vista funcional, a estrutura interna do código contém diversas fragilidades de design que impactam diretamente a sua evolução, legibilidade e facilidade de testes.

Sua missão como aluno é analisar o código-fonte, identificar os pontos de melhoria estruturais e aplicar as refatorações necessárias para elevar a qualidade do projeto sem alterar a saída gerada no console.

---

## 📋 Proposta de Exercício

1. **Análise Crítica:** Esquadrinhe o método `print` e identifique as violações de boas práticas de programação e orientação a objetos.
2. **Mapeamento:** Liste quais sintomas de código deteriorado (*Code Smells*) estão presentes e quais princípios de design foram violados.
3. **Refatoração:** Aplique técnicas de refatoração para transformar o código em uma solução limpa, bem estruturada e fácil de manter.

---

## 🛠️ Requisitos para Execução

* **Linguagem:** Java 17 ou superior

### Como Executar

```bash
javac UsuarioPrinter.java
java UsuarioPrinter
```
