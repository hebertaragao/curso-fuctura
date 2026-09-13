## 📑 Sumário

- [🛠️ Setup do Ambiente — Aulas de Banco de Dados com Java](#️-setup-do-ambiente--aulas-de-banco-de-dados-com-java)
  - [✅ 1️⃣ Instalar o Java 21](#️-1️⃣-instalar-o-java-21)
  - [✅ 2️⃣ Instalar o Eclipse IDE](#️-2️⃣-instalar-o-eclipse-ide)
  - [✅ 3️⃣ Instalar o PostgreSQL](#️-3️⃣-instalar-o-postgresql)
  - [✅ 4️⃣ Instalar o DBeaver](#️-4️⃣-instalar-o-dbeaver)
  - [🔌 5️⃣ Configurar Conexão no DBeaver](#-5️⃣-configurar-conexão-no-dbeaver)
  - [🧪 6️⃣ Testar o PostgreSQL](#-6️⃣-testar-o-postgresql)
  - [☕ 7️⃣ JDBC no Eclipse](#-7️⃣-jdbc-no-eclipse-importante-para-as-próximas-aulas)
  - [🎯 Ambiente pronto!](#-ambiente-pronto)
  - [❓ Problemas comuns](#-problemas-comuns)
  - [👍 Pronto para começar!](#-pronto-para-começar)
- [👍 Criando um Projeto Maven no Eclipse](#criando-um-projeto-maven-no-eclipse)
- [☕ Hello World JDBC — Primeira Conexão com o PostgreSQL](#-hello-world-jdbc--primeira-conexão-com-o-postgresql)
- [🎓 Projeto — Sistema de Gerenciamento Acadêmico (Console + JDBC)](#-projeto--sistema-de-gerenciamento-acadêmico-console--jdbc)
  - [🎯 Objetivo Geral](#-objetivo-geral)
  - [🧩 Estrutura Conceitual](#-estrutura-conceitual)
  - [🗄 Estrutura do Banco de Dados](#-estrutura-do-banco-de-dados)
  - [🔄 Evolução do Modelo](#-evolução-do-modelo)
  - [🧱 Diagrama ER](#-diagrama-er)
  - [🧱 Arquitetura do Projeto](#-arquitetura-do-projeto)
  - [🗺 Diagrama da Estrutura do Projeto](#-diagrama-da-estrutura-do-projeto)
  - [🖥 Fluxo de Navegação do Sistema](#-fluxo-de-navegação-do-sistema)
  - [🎯 Exemplos de JOIN](#-exemplos-de-join)
- [🧩 API JDBC — Principais Interfaces e Classes](#-api-jdbc--principais-interfaces-e-classes)

# 🛠️ Setup do Ambiente — Aulas de Banco de Dados com Java

Este guia orienta a configuração do ambiente necessário para as nossas aulas iniciais.  
Vamos utilizar:

- **Java 21**
- **Eclipse IDE**
- **PostgreSQL**
- **DBeaver**

---

## ✅ 1️⃣ Instalar o Java 21

### 🔹 Verifique se já possui Java instalado
Abra o terminal / prompt de comando e execute:

```bash
java -version
```

Se aparecer **Java 21**, você já está pronto.  
Se não aparecer ou estiver em versão inferior, instale:

### 🔹 Download
Baixe o **JDK 21 (LTS)** em:

- https://jdk.java.net/21/  
ou  
- https://www.oracle.com/java/technologies/downloads/

Instale normalmente.

### 🔹 Verifique após instalar
```bash
java -version
```

---

## ✅ 2️⃣ Instalar o Eclipse IDE

Baixe o Eclipse em:

- https://www.eclipse.org/downloads/

Recomendado:
> **Eclipse IDE for Java Developers**

Após baixar:
- Instale ou extraia
- Abra o Eclipse
- Escolha um workspace (pasta de projetos)

Pronto! ✔️

---

## ✅ 3️⃣ Instalar o PostgreSQL

Baixe o PostgreSQL em:

- https://www.postgresql.org/download/

Durante a instalação:
1. Defina uma senha para o usuário **postgres** → guarde essa senha

Após instalar, verifique:
- O PostgreSQL deve iniciar automaticamente
- Porta padrão: **5432**

---

## ✅ 4️⃣ Instalar o DBeaver

Baixe em:

- https://dbeaver.io/download/

Instale normalmente.

---

## 🔌 5️⃣ Configurar Conexão no DBeaver

1. Abra o **DBeaver**
2. Clique em **New Database Connection**
3. Selecione **PostgreSQL**
4. Preencha as informações:

| Campo        | Valor sugerido |
|--------------|----------------|
| Host         | localhost       |
| Port         | 5432            |
| Database     | postgres        |
| User         | postgres        |
| Password     | (senha definida na instalação) |

Clique em **Test Connection**  
Se estiver tudo OK → **Finish**

---

## 🧪 6️⃣ Testar o PostgreSQL

No DBeaver, execute:

```sql
SELECT version();
```

Se aparecer a versão do PostgreSQL → Banco funcionando! ✔️

---

## ☕ 7️⃣ JDBC no Eclipse (Importante para as próximas aulas)

Para conectar Java ao PostgreSQL será necessário o **driver JDBC**.

Quando criarmos o projeto Java no Eclipse:
1. Crie um projeto Java
2. Adicione o driver JDBC

Driver oficial:
https://jdbc.postgresql.org/

Nas próximas aulas também vamos ver como usar **Maven** para gerenciar dependências.

---

## 🎯 Ambiente pronto!

Se tudo estiver certo, você terá:
✔️ Java funcionando  
✔️ Eclipse instalado  
✔️ PostgreSQL ativo  
✔️ DBeaver conectado ao banco  

Nas próximas aulas vamos:
- Criar banco e tabelas
- Executar comandos SQL
- Integrar Java com PostgreSQL

---
# Criando um Projeto Maven no Eclipse

Este documento apresenta um **passo a passo completo para criar um projeto Maven no Eclipse**, ideal para aulas iniciais de Java e projetos introdutórios.

---

## 🧩 Pré-requisitos

Antes de começar, certifique-se de que você possui:

- **Java (JDK) instalado** (Java 17 ou Java 21)
- **Eclipse IDE**
  - Recomendado: *Eclipse IDE for Java Developers*
- **Maven**
  - O Eclipse já vem com Maven integrado (plugin *m2e*)

---

## 🚀 Passo a passo: Criando um projeto Maven no Eclipse

### 1️⃣ Abrir o Eclipse

Abra o Eclipse normalmente e selecione um **Workspace**, que é a pasta onde seus projetos ficarão armazenados.

---

### 2️⃣ Criar um novo projeto Maven

1. No menu superior, clique em:
   ```
   File → New → Other...
   ```
2. Na janela que será exibida:
   - Expanda a opção **Maven**
   - Selecione **Maven Project**
   - Clique em **Next**

---

### 3️⃣ Configurar o projeto Maven

1. Marque a opção:
   ```
   Create a simple project (skip archetype selection)
   ```
   Essa opção é recomendada para projetos simples ou aulas iniciais.
2. Clique em **Next**

---

### 4️⃣ Informar os dados do projeto

Preencha os campos conforme o exemplo abaixo:

- **Group Id**
  ```
  br.com.fuctura
  ```
  Representa a organização ou domínio do projeto.

- **Artifact Id**
  ```
  projeto-maven
  ```
  Nome do projeto / artefato.

- **Version**
  ```
  1.0-SNAPSHOT
  ```
  O sufixo `SNAPSHOT` indica que o projeto está em desenvolvimento.

- **Packaging**
  ```
  jar
  ```
  Utilizado para aplicações Java comuns.

- **Name** (opcional)
  ```
  Projeto Maven Exemplo
  ```

- **Description** (opcional)
  ```
  Projeto Maven para aulas de Java
  ```

Após preencher todos os campos, clique em **Finish**.

---

### 5️⃣ Estrutura padrão do projeto Maven

Após a criação, o Eclipse irá gerar automaticamente a seguinte estrutura:

```
projeto-maven
 ├── src/main/java
 ├── src/main/resources
 ├── src/test/java
 └── pom.xml
```

**Descrição das pastas:**

- `src/main/java` → Código-fonte principal da aplicação
- `src/main/resources` → Arquivos de configuração e recursos
- `src/test/java` → Código de testes
- `pom.xml` → Arquivo central de configuração do Maven

---

## 🛠️ Configurar a versão do Java no Maven

1. Abra o arquivo **pom.xml**
2. Adicione ou ajuste as propriedades abaixo:

```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
</properties>
```

Ajuste a versão do Java conforme o padrão adotado na aula ou no projeto.

---

## ▶️ Criar uma classe Java de teste

1. Clique com o botão direito em:
   ```
   src/main/java
   ```
2. Selecione:
   ```
   New → Class
   ```
3. Preencha os campos:
   - **Package**: `br.com.fuctura`
   - **Name**: `Main`

Exemplo de código:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Projeto Maven funcionando!");
    }
}
```

---

## 📦 Atualizar dependências Maven

Caso o projeto apresente erros ou dependências não resolvidas:

1. Clique com o botão direito no projeto
2. Selecione:
   ```
   Maven → Update Project
   ```
3. Clique em **OK**

---

## ✅ Conclusão

O projeto Maven está corretamente configurado e pronto para:

- Adicionar dependências no arquivo `pom.xml`
- Trabalhar com JDBC, JPA, Spring e outras bibliotecas
- Gerar builds utilizando o comando:
  ```
  mvn package
  ```
---

## ❓ Problemas comuns

### 🔸 Erro ao conectar no DBeaver
- Verifique se o PostgreSQL está em execução
- Verifique host, porta, usuário e senha
- Verifique firewall

### 🔸 `java: command not found`
- Java não instalado
- Variáveis de ambiente não configuradas corretamente

---

## 👍 Pronto para começar!

# ☕ Hello World JDBC — Primeira Conexão com o PostgreSQL

Antes de criarmos DAOs, Services e toda a arquitetura do sistema, vamos fazer o nosso primeiro teste de acesso ao banco de dados.

O objetivo é simples: obter uma conexão utilizando o método `getConnection()` da classe `DriverManager`.

Esse exemplo representa o equivalente ao **"Hello World" do JDBC**.

---

## 📦 Pré-requisitos

Certifique-se de que:

- O PostgreSQL está em execução
- A dependência JDBC do PostgreSQL foi adicionada ao `pom.xml`

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.3</version>
</dependency>
```

---

## 🚀 Exemplo Completo

Crie uma classe chamada `Main`:

```java
package br.com.fuctura;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String usuario = "postgres";
        String senha = "sua_senha";

        try (Connection connection =
                DriverManager.getConnection(url, usuario, senha)) {

            System.out.println("Conexão realizada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco.");
            e.printStackTrace();
        }
    }
}
```

---

## 🔍 Entendendo a URL JDBC

A URL utilizada para conexão segue o formato:

```text
jdbc:postgresql://host:porta/banco
```

Exemplo:

```text
jdbc:postgresql://localhost:5432/postgres
```

Onde:

| Parte | Descrição |
|---------|---------|
| `jdbc` | Protocolo JDBC |
| `postgresql` | Driver utilizado |
| `localhost` | Servidor do banco |
| `5432` | Porta padrão do PostgreSQL |
| `postgres` | Nome do banco |

---

## ⚙️ O que acontece quando chamamos `getConnection()`?

Ao executar:

```java
DriverManager.getConnection(url, usuario, senha);
```

o JDBC realiza os seguintes passos:

1. Localiza o driver PostgreSQL disponível no projeto.
2. Solicita uma conexão ao banco de dados.
3. Autentica o usuário informado.
4. Cria uma sessão com o banco.
5. Retorna um objeto do tipo `Connection`.

Esse objeto será utilizado futuramente para executar comandos SQL.

---

## ✅ Resultado Esperado

Se tudo estiver configurado corretamente:

```text
Conexão realizada com sucesso!
```

---

## ❌ Possíveis Erros

Caso a conexão falhe, verifique:

- PostgreSQL está em execução
- Usuário e senha estão corretos
- Banco de dados existe
- Porta está correta
- Dependência JDBC foi adicionada ao Maven

---

## 🎯 Conclusão

Neste exemplo realizamos a operação mais básica do JDBC:

- Utilizamos o `DriverManager`
- Obtivemos uma `Connection`
- Estabelecemos comunicação com o PostgreSQL

---

# 🎓 Projeto — Sistema de Gerenciamento Acadêmico (Console + JDBC)

Este projeto será desenvolvido ao longo das aulas para simular um sistema acadêmico.

Nosso objetivo é trabalhar conceitos de:

- JDBC
- PostgreSQL
- Arquitetura em Camadas
- DAO
- Service
- Relacionamentos
- Chaves Estrangeiras
- JOINs
- Boas práticas de organização de código

---

## 🎯 Objetivo Geral

Criar uma aplicação console em Java que permita:

- Gerenciar alunos
- Gerenciar professores
- Gerenciar disciplinas
- Realizar matrículas de alunos em disciplinas
- Consultar informações utilizando JOINs
- Persistir dados em PostgreSQL através do JDBC

---

## 🧩 Estrutura Conceitual

O sistema contempla as seguintes entidades:

### 👨‍🎓 Aluno

Representa os estudantes da instituição.

### 👨‍🏫 Professor

Representa os professores responsáveis pelas disciplinas.

### 📚 Disciplina

Representa os componentes curriculares ofertados.

### 📝 Matrícula

Representa a inscrição de um aluno em uma disciplina.

---

## 🗄 Estrutura do Banco de Dados

### Tabela Aluno

```sql
CREATE TABLE aluno (
    matricula VARCHAR(255) PRIMARY KEY,
    nome VARCHAR(100),
    idade INT,
    celular VARCHAR(15)
);
```

### Tabela Professor

```sql
CREATE TABLE professor (
    nome VARCHAR(100),
    telefone VARCHAR(15),
    matricula VARCHAR(32) PRIMARY KEY
);
```

### Tabela Disciplina

```sql
CREATE TABLE disciplina (
    nome VARCHAR(100),
    carga_horaria VARCHAR(15),
    codigo VARCHAR(32) PRIMARY KEY
);
```

---

## 🔄 Evolução do Modelo

### Relacionamento Professor → Disciplina

Uma disciplina possui um professor responsável.

Adicionar coluna:

```sql
ALTER TABLE disciplina
ADD COLUMN professor_matricula VARCHAR(32);
```

Adicionar chave estrangeira:

```sql
ALTER TABLE disciplina
ADD CONSTRAINT fk_disciplina_professor
FOREIGN KEY (professor_matricula)
REFERENCES professor(matricula);
```

---

### Relacionamento Aluno ↔ Disciplina

Um aluno pode cursar várias disciplinas.

Uma disciplina pode possuir vários alunos.

Para representar esse relacionamento utilizaremos a entidade **Matrícula**.

```sql
CREATE TABLE matricula (
    id SERIAL PRIMARY KEY,
    data_matricula DATE,
    aluno_matricula VARCHAR(255),
    disciplina_codigo VARCHAR(32)
);
```

Adicionar chave estrangeira para aluno:

```sql
ALTER TABLE matricula
ADD CONSTRAINT fk_matricula_aluno
FOREIGN KEY (aluno_matricula)
REFERENCES aluno(matricula);
```

Adicionar chave estrangeira para disciplina:

```sql
ALTER TABLE matricula
ADD CONSTRAINT fk_matricula_disciplina
FOREIGN KEY (disciplina_codigo)
REFERENCES disciplina(codigo);
```

---

## 🧱 Diagrama ER

```mermaid
erDiagram

    ALUNO {
        VARCHAR matricula PK
        STRING nome
        INTEGER idade
        STRING celular
    }

    PROFESSOR {
        VARCHAR matricula PK
        STRING nome
        STRING telefone
    }

    DISCIPLINA {
        VARCHAR codigo PK
        STRING nome
        STRING carga_horaria
        VARCHAR professor_matricula FK
    }

    MATRICULA {
        LONG id PK
        DATE data_matricula
        VARCHAR aluno_matricula FK
        VARCHAR disciplina_codigo FK
    }

    PROFESSOR ||--o{ DISCIPLINA : ministra

    ALUNO ||--o{ MATRICULA : realiza
    DISCIPLINA ||--o{ MATRICULA : possui
```

---

## 🧱 Arquitetura do Projeto

### 🖥 Camada de Aplicação (`application`)

Responsável pelo ponto de entrada da aplicação.

- `Application`

---

### ⌨️ Camada Console (`console`)

Responsável pela interação com o usuário.

- `MenuPrincipal`

---

### 📦 Camada de Domínio (`domain`)

Representa as entidades do negócio.

- `Aluno`
- `Professor`
- `Disciplina`
- `Matricula`

---

### 🧠 Camada de Serviços (`service`)

Contém as regras de negócio.

- `AlunoService`
- `ProfessorService`
- `DisciplinaService`
- `MatriculaService`

---

### 💾 Camada DAO (`dao`)

Responsável pelo acesso ao banco de dados.

- `AlunoDAO`
- `ProfessorDAO`
- `DisciplinaDAO`
- `MatriculaDAO`

---

### 🗄 Camada Infrastructure (`infrastructure`)

Responsável por configurações e conexões JDBC.

- `DatabaseConfig`
- `ConnectionFactory`

---

## 🗺 Diagrama da Estrutura do Projeto

```mermaid
classDiagram

namespace application {
    class Application
}

namespace console {
    class ConsoleUI
    class MenuPrincipal
}

namespace domain {

    class Aluno {
        - String matricula
        - String nome
        - Integer idade
        - String celular
    }

    class Professor {
        - String matricula
        - String nome
        - String telefone
    }

    class Disciplina {
        - String codigo
        - String nome
        - String cargaHoraria
        - String professorMatricula
    }

    class Matricula {
        - Long id
        - LocalDate dataMatricula
        - String alunoMatricula
        - String disciplinaCodigo
    }
}

namespace service {
    class AlunoService
    class ProfessorService
    class DisciplinaService
    class MatriculaService
}

namespace dao {
    class AlunoDAO
    class ProfessorDAO
    class DisciplinaDAO
    class MatriculaDAO
}

namespace infrastructure {
    class DatabaseConfig
    class ConnectionFactory
}

Application --> ConsoleUI
ConsoleUI --> MenuPrincipal

MenuPrincipal --> AlunoService
MenuPrincipal --> ProfessorService
MenuPrincipal --> DisciplinaService
MenuPrincipal --> MatriculaService

AlunoService --> AlunoDAO
ProfessorService --> ProfessorDAO
DisciplinaService --> DisciplinaDAO
MatriculaService --> MatriculaDAO

AlunoDAO --> ConnectionFactory
ProfessorDAO --> ConnectionFactory
DisciplinaDAO --> ConnectionFactory
MatriculaDAO --> ConnectionFactory

ConnectionFactory --> DatabaseConfig

Professor "1" --> "*" Disciplina : ministra

Aluno "1" --> "*" Matricula : realiza
Disciplina "1" --> "*" Matricula : possui
```
---

## 🖥 Fluxo de Navegação do Sistema

O sistema será executado através de menus em modo texto utilizando JLine.

### Menu Principal

```text
=================================
      SISTEMA ACADÊMICO
=================================

1 - Alunos
2 - Professores
3 - Disciplinas
4 - Matrículas
0 - Sair

Escolha:
```

---

## 👨‍🎓 Módulo de Alunos

### Menu

```text
=================================
          ALUNOS
=================================

1 - Cadastrar
2 - Listar
3 - Buscar
4 - Excluir
0 - Voltar

Escolha:
```

### Fluxo: Cadastrar Aluno

```text
=================================
      CADASTRAR ALUNO
=================================

Matrícula: 20250001
Nome: João Silva
Idade: 20
Celular: (81)99999-9999

Aluno cadastrado com sucesso!
```

### Fluxo: Listar Alunos

```text
=================================
        LISTA DE ALUNOS
=================================

20250001 - João Silva
20250002 - Maria Oliveira
20250003 - Pedro Santos

Pressione ENTER para continuar...
```

### Fluxo: Buscar Aluno

```text
=================================
        BUSCAR ALUNO
=================================

Matrícula: 20250001

Dados encontrados:

Matrícula: 20250001
Nome: João Silva
Idade: 20
Celular: (81)99999-9999
```

### Fluxo: Excluir Aluno

```text
=================================
       EXCLUIR ALUNO
=================================

Matrícula: 20250001

Aluno removido com sucesso!
```

---

## 👨‍🏫 Módulo de Professores

### Menu

```text
=================================
        PROFESSORES
=================================

1 - Cadastrar
2 - Listar
3 - Buscar
4 - Excluir
0 - Voltar

Escolha:
```

### Fluxo: Cadastrar Professor

```text
=================================
    CADASTRAR PROFESSOR
=================================

Matrícula: P001
Nome: Maria Souza
Telefone: (81)98888-8888

Professor cadastrado com sucesso!
```

### Fluxo: Listar Professores

```text
=================================
     LISTA DE PROFESSORES
=================================

P001 - Maria Souza
P002 - Carlos Lima
P003 - Fernanda Melo

Pressione ENTER para continuar...
```

### Fluxo: Buscar Professor

```text
=================================
      BUSCAR PROFESSOR
=================================

Matrícula: P001

Dados encontrados:

Matrícula: P001
Nome: Maria Souza
Telefone: (81)98888-8888
```

### Fluxo: Excluir Professor

```text
=================================
      EXCLUIR PROFESSOR
=================================

Matrícula: P001

Professor removido com sucesso!
```

---

## 📚 Módulo de Disciplinas

### Menu

```text
=================================
        DISCIPLINAS
=================================

1 - Cadastrar
2 - Listar
3 - Buscar
4 - Excluir
0 - Voltar

Escolha:
```

### Fluxo: Cadastrar Disciplina

```text
=================================
    CADASTRAR DISCIPLINA
=================================

Código: BD001
Nome: Banco de Dados
Carga Horária: 60h

Professor responsável:

P001 - Maria Souza

Disciplina cadastrada com sucesso!
```

### Fluxo: Listar Disciplinas

```text
=================================
      LISTA DE DISCIPLINAS
=================================

BD001 - Banco de Dados
POO001 - Programação Orientada a Objetos
WEB001 - Desenvolvimento Web

Pressione ENTER para continuar...
```

### Fluxo: Buscar Disciplina

```text
=================================
      BUSCAR DISCIPLINA
=================================

Código: BD001

Dados encontrados:

Código: BD001
Nome: Banco de Dados
Carga Horária: 60h
Professor: Maria Souza
```

### Fluxo: Excluir Disciplina

```text
=================================
     EXCLUIR DISCIPLINA
=================================

Código: BD001

Disciplina removida com sucesso!
```

---

## 📝 Módulo de Matrículas

### Menu

```text
=================================
         MATRÍCULAS
=================================

1 - Matricular aluno
2 - Cancelar matrícula
3 - Consultar disciplinas de um aluno
4 - Consultar alunos de uma disciplina
5 - Relatório completo
0 - Voltar

Escolha:
```

### Fluxo: Matricular Aluno

```text
=================================
      MATRICULAR ALUNO
=================================

Matrícula do aluno: 20250001

Disciplinas disponíveis:

BD001 - Banco de Dados
POO001 - Programação Orientada a Objetos
WEB001 - Desenvolvimento Web

Código da disciplina: BD001

Matrícula realizada com sucesso!
```

### Fluxo: Cancelar Matrícula

```text
=================================
      CANCELAR MATRÍCULA
=================================

Matrícula do aluno: 20250001

Disciplinas matriculadas:

BD001 - Banco de Dados
POO001 - Programação Orientada a Objetos

Código da disciplina: BD001

Matrícula cancelada com sucesso!
```

### Fluxo: Consultar Disciplinas de um Aluno

```text
=================================
    DISCIPLINAS DO ALUNO
=================================

Matrícula do aluno: 20250001

Aluno: João Silva

Disciplinas:

BD001 - Banco de Dados
POO001 - Programação Orientada a Objetos
WEB001 - Desenvolvimento Web
```

### Fluxo: Consultar Alunos de uma Disciplina

```text
=================================
   ALUNOS DA DISCIPLINA
=================================

Código da disciplina: BD001

Disciplina: Banco de Dados
Professor: Maria Souza

Alunos matriculados:

20250001 - João Silva
20250002 - Pedro Santos
20250003 - Ana Costa
```

### Fluxo: Relatório Completo

```text
=================================
      RELATÓRIO COMPLETO
=================================

Aluno                Disciplina             Professor
---------------------------------------------------------
João Silva           Banco de Dados         Maria Souza
João Silva           Programação OO         Carlos Lima
Pedro Santos         Banco de Dados         Maria Souza
Ana Costa            Desenvolvimento Web    Fernanda Melo
```

---

## 🎯 Exemplos de JOIN

### Disciplinas e seus Professores

```sql
SELECT
    d.codigo,
    d.nome AS disciplina,
    p.nome AS professor
FROM disciplina d
INNER JOIN professor p
    ON p.matricula = d.professor_matricula;
```

### Alunos, Disciplinas e Professores

```sql
SELECT
    a.nome AS aluno,
    d.nome AS disciplina,
    p.nome AS professor
FROM matricula m
INNER JOIN aluno a
    ON a.matricula = m.aluno_matricula
INNER JOIN disciplina d
    ON d.codigo = m.disciplina_codigo
INNER JOIN professor p
    ON p.matricula = d.professor_matricula;
```
---
## 📦 Dependências do Projeto

O projeto utilizará **Maven** para gerenciamento de dependências.  
Certifique-se de que o arquivo `pom.xml` contenha as seguintes bibliotecas:

### 🗄️ Driver JDBC do PostgreSQL
Responsável por permitir a comunicação entre o Java e o banco PostgreSQL.

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.3</version>
</dependency>
```

### ⌨️ JLine – Interface de Console

Usaremos o JLine para criar menus mais amigáveis e melhorar a interação via terminal.

```xml
<dependency>
    <groupId>org.jline</groupId>
    <artifactId>jline</artifactId>
    <version>3.26.0</version>
</dependency>
```

### ⌨️ Lombok

O **Lombok** é uma **biblioteca Java** que reduz drasticamente a **verbosidade do código**, gerando automaticamente código repetitivo **em tempo de compilação**.

📌 Ele elimina a necessidade de escrever manualmente:
- Getters e Setters
- Construtores
- `toString()`
- `equals()` e `hashCode()`
- Builders
- Logs

Tudo isso é feito por meio de **anotações**.

```xml
<!-- Source: https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.42</version>
    <scope>compile</scope>
</dependency>
```
---

### Snippet


```java
package br.com.fuctura;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class MenuPrincipal {

	private LineReader reader;

	public MenuPrincipal() throws Exception {
		Terminal terminal = TerminalBuilder.builder().system(true).build();
		reader = LineReaderBuilder.builder().terminal(terminal).build();
	}

	public void iniciar() {
		String opcao;

		do {
			System.out.println("\n====== 📚 SISTEMA DE BIBLIOTECA ======");
			System.out.println("1) Gerenciar Livros");
			System.out.println("2) Gerenciar Usuários");
			System.out.println("3) Registrar Empréstimo (futuro)");
			System.out.println("4) Registrar Devolução (futuro)");
			System.out.println("0) Sair");
			System.out.println("=====================================");

			opcao = reader.readLine("Selecione uma opção: ");

			switch (opcao) {
			case "1" -> menuLivros();
			case "2" -> menuUsuarios();
			case "3" -> System.out.println(">>> Empréstimo (a implementar)");
			case "4" -> System.out.println(">>> Devolução (a implementar)");
			case "0" -> System.out.println("Encerrando...");
			default -> System.out.println("Opção inválida!");
			}

		} while (!opcao.equals("0"));
	}

	private void menuLivros() {
		String opcao;

		do {
			System.out.println("\n------ MENU LIVROS ------");
			System.out.println("1) Cadastrar Livro");
			System.out.println("2) Listar Livros");
			System.out.println("3) Buscar Livro");
			System.out.println("0) Voltar");

			opcao = reader.readLine("Escolha: ");

			switch (opcao) {
			case "1" -> System.out.println("cadastrar...");
			case "2" -> System.out.println("b...");
			case "3" -> System.out.println("c...");
			case "0" -> System.out.println("Voltando...");
			default -> System.out.println("Opção inválida!");
			}

		} while (!opcao.equals("0"));
	}

	private void menuUsuarios() {
		String opcao;

		do {
			System.out.println("\n------ MENU USUÁRIOS ------");
			System.out.println("1) Cadastrar Usuário");
			System.out.println("2) Listar Usuários");
			System.out.println("0) Voltar");

			opcao = reader.readLine("Escolha: ");

			switch (opcao) {
			case "0" -> System.out.println("Voltando...");
			default -> System.out.println("Opção inválida!");
			}

		} while (!opcao.equals("0"));
	}
}
```

## 🧩 API JDBC — Principais Interfaces e Classes

A API JDBC é composta por um conjunto de **interfaces e classes fundamentais** que permitem a comunicação entre aplicações Java e bancos de dados relacionais.

Cada elemento da API possui um papel bem definido dentro do fluxo de acesso a dados.

---

## 🚦 `DriverManager`

O `DriverManager` é o **ponto central de gerenciamento dos drivers JDBC**.

### Funções principais:
- Localizar o driver JDBC apropriado
- Estabelecer conexões com o banco de dados
- Gerenciar múltiplos drivers registrados na JVM

📌 Normalmente, o driver é carregado automaticamente quando a dependência é adicionada ao projeto.

### Uso conceitual:
> “O `DriverManager` sabe qual driver usar com base na URL JDBC.”

---

## 🔗 `Connection`

A interface `Connection` representa uma **conexão ativa com o banco de dados**.

### Responsabilidades:
- Criar objetos `Statement` e `PreparedStatement`
- Controlar transações (`commit`, `rollback`)
- Definir modo de auto-commit
- Encerrar a comunicação com o banco

📌 Cada instância de `Connection` corresponde a uma sessão aberta no banco.

⚠️ Conexões devem ser sempre fechadas após o uso.

---

## 📝 `Statement`

O `Statement` é usado para executar comandos SQL **estáticos**, sem parâmetros.

### Características:
- Executa SQL diretamente
- Indicado apenas para testes ou comandos simples
- Não protege contra SQL Injection

📌 Seu uso é **desencorajado em sistemas reais**.

---

## 🔐 `PreparedStatement`

O `PreparedStatement` é uma versão mais segura e eficiente do `Statement`.

### Vantagens:
- Suporte a parâmetros
- Prevenção contra SQL Injection
- Melhor desempenho
- Reutilização de comandos

📌 É o tipo de comando **recomendado** para praticamente todos os casos.

Conceito importante:
> “O SQL é compilado uma vez, os dados são fornecidos separadamente.”

---

## 📊 `ResultSet`

O `ResultSet` representa o **conjunto de dados retornado por uma consulta SQL**.

### Características:
- Navegação linha a linha
- Acesso aos dados por nome ou índice da coluna
- Conversão automática de tipos SQL para Java

📌 O `ResultSet` funciona como um **cursor** sobre os dados.


## 📊 ResultSet — Conceitos, Funcionamento e Boas Práticas

O **`ResultSet`** é a interface da API JDBC que representa o **resultado de uma consulta SQL (`SELECT`)**.
Ele é responsável por disponibilizar, de forma controlada, os dados retornados pelo banco de dados para a aplicação Java.

Diferente de uma lista comum, o `ResultSet` **não carrega os dados como objetos Java prontos**.
Ele funciona como um **cursor** que percorre os registros retornados pelo banco.

---

## 🧠 Conceito fundamental: Cursor

Quando um `ResultSet` é criado:
- O cursor começa **antes da primeira linha**
- Nenhum dado está acessível ainda
- É obrigatório mover o cursor para acessar os dados

```java
ResultSet rs = ps.executeQuery();
// cursor está antes da primeira linha
```

📌 Somente após chamar next() o cursor aponta para uma linha válida.

---

▶️ Navegação básica

```java
while (rs.next()) { // leitura dos dados da linha atual } `
```

- `next()` move o cursor para a próxima linha

- Retorna `false` quando não há mais registros

📌 Esse é o padrão mais comum de uso.

---

## 🧾 `SQLException`

A classe `SQLException` representa **erros relacionados ao banco de dados**.

### Pode indicar:
- Erros de conexão
- Problemas de sintaxe SQL
- Violação de integridade
- Falhas de transação

📌 Sempre deve ser tratada ou propagada adequadamente.

---

## 🔄 Fluxo conceitual da API JDBC

1. `DriverManager` localiza o driver
2. Uma `Connection` é criada
3. Um `PreparedStatement` é preparado
4. O SQL é executado
5. O resultado é lido via `ResultSet`
6. Recursos são fechados

---

## 📚 Conclusão

A API JDBC oferece:
- Controle total sobre o acesso a dados
- Uma base sólida para compreender frameworks ORM
- Clareza sobre execução de SQL e transações

> “Entender a API JDBC é entender o coração do acesso a dados em Java.”
