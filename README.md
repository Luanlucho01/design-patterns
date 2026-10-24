# Atividade

Que tal refletirmos um pouco sobre o que acontece com esse código quando novos requisitos são adicionados?

---

## 🎯 Objetivo da Atividade

Altere o código da classe PlanetasPrinter que os planetas também possam ser impressos.
Tente fazer isso com o menor esforço possível.

Verifique a classe [br.pucpr.Planet](https://github.com/ViniGodoy/design-patterns/blob/aula-01-ex/src/main/java/br/pucpr/planet/Planet.java)

O formato de impressão da tabela (bordas, temas) deve ser exatamente igual ao da tabela de usuários.

Quanto aos dados:
1. Devem ser impressas as seguintes colunas "Nome", "Diâmetro", "Dist. sol (km)", "Dist. sol (ua)", e "Tipo". Observação uma unidade astronomica (UA) é equivalente à distância da Terra ao Sol.
2. O diametro do planeta deve ser impresso com 1 casa decimal e separadores de milhar.
3. A distância até o sol em km deve ser impressa em um número inteiro com separadores de milhar.  
4. Já a distância em UA deve ser impressa com duas casas decimais com separadores de milhar.
5. O tipo deve ser impresso como "Rochoso", "Gososo", "Gelado", "Anão"

## 📋 Reflexão final

Ao final da implementação reflita:

1. Sua solução ficou com muito código duplicado?
2. O que aconteceria se uma terceira classe tivesse que ser adicionada?

---

## 🛠️ Requisitos para Execução

* **Linguagem:** Java 17 ou superior

### Como Executar

```bash
javac PlanetasPrinter.java
java PlanetasPrinter
```
