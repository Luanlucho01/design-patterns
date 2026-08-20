# Análise e Refatoração — UsuarioPrinter

Resolução da proposta de exercício descrita no [README](README.md).

---

## Análise crítica (método `print` original)
O método concentrava validação, escolha de tema, formatação de campos, montagem da tabela e impressão — responsabilidades distintas em um único fluxo procedural.

Problemas observados:

- Método longo e difícil de ler, com comentários compensando a falta de abstração.
- Formatação de nome, e-mail e CPF misturada à orquestração da tabela.
- Parâmetros primitivos (`boolean`, `String`) carregando significado de domínio (tema, máscara, recuo).
- Dependência concreta de `ArrayList` em vez da abstração `List`.
- Borda inferior e impressão estavam **dentro** do `for`, causando reimpressão acumulada a cada usuário.
- `alignRight` não alinhava colunas à direita; apenas aplicava recuo à esquerda na saída.

---

## Code smells e princípios violados

| Code Smell | Onde aparecia |

|---|---|
| *Long Method* | `print` fazia validação, tema, formatação, renderização e I/O |
| *Large Class* / *God Class* (incipiente) | `UsuarioPrinter` acumulava modelo + formatação + apresentação |
| *Primitive Obsession* | tema como `String`; flags `boolean` sem intenção explícita |
| *Feature Envy* | `print` manipulava intensamente os campos de `Usuario` |
| *Magic Numbers* | `74`, `20`, `17`, `11`, recuo com espaços literais |
| *Comments* | comentários explicando blocos que deveriam ser métodos |
| *Misleading Name* | `alignRight` não descrevia o comportamento real |
| *Inappropriate Intimacy* / acoplamento | lógica de CPF/e-mail embutida no renderer |

**Princípios violados:**

- **SRP** (Single Responsibility): uma classe/método com várias razões para mudar.
- **OCP** (Open/Closed): novo tema ou nova regra de formatação exigia editar `print`.
- **DIP** (Dependency Inversion): acoplamento a `ArrayList` e a `System.out` no mesmo fluxo.
- **Tell, Don't Ask** / encapsulamento: dados de `Usuario` eram extraídos e tratados externamente sem abstração.
- **KISS / Clean Code**: nomes pouco expressivos e fluxo difícil de testar unitariamente.

---

## Refatoração aplicada

Técnicas utilizadas: *Extract Class*, *Extract Method*, *Replace Primitive with Object/Enum*, *Introduce Parameter Object*, *Replace Type Code with Enum*.

Estrutura resultante:

| Tipo | Papel |

|---|---|
| `Usuario` | Modelo de domínio |
| `Tema` | Caracteres de borda por tema |
| `OpcoesImpressao` | Agrupa máscara de CPF, recuo e tema |
| `FormatadorUsuario` | Regras de formatação/validação de campos |
| `UsuarioPrinter` | Orquestra montagem da tabela e impressão |


Benefícios: responsabilidades separadas, nomes alinhados à intenção, formatação testável de forma isolada e correção do laço que reimprimia a tabela a cada linha.

