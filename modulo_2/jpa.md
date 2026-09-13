# Introdução ao JPA (Jakarta Persistence API)

## O que é JPA?

JPA (Jakarta Persistence API) é uma **especificação** da plataforma
Jakarta EE que define como aplicações Java devem mapear, persistir e
consultar dados relacionais usando **objetos Java**.

> ⚠️ Importante: JPA **não é uma implementação**.\
> Ela define **contratos e regras**.\
> Implementações comuns: **Hibernate**, **EclipseLink**, **OpenJPA**.

------------------------------------------------------------------------

## O problema que o JPA resolve

Antes do JPA, o desenvolvimento com JDBC exigia:

-   SQL espalhado pelo código
-   Conversão manual entre ResultSet e objetos
-   Alto acoplamento entre código e banco
-   Código repetitivo e difícil de manter

O JPA surge para **simplificar a persistência de dados** e aumentar a
produtividade.

------------------------------------------------------------------------

## Objetivos do JPA

-   Mapear classes Java para tabelas do banco
-   Reduzir código boilerplate de JDBC
-   Padronizar o acesso a dados em Java
-   Desacoplar a aplicação do banco de dados
-   Facilitar manutenção e evolução do sistema

------------------------------------------------------------------------

## Principais conceitos do JPA

### Entidade

Classe Java que representa uma tabela do banco.

``` java
@Entity
public class Usuario {
    @Id
    private Long id;
    private String nome;
}
```

------------------------------------------------------------------------

### EntityManager

Responsável por operações de persistência:

-   persist
-   find
-   merge
-   remove
-   createQuery

------------------------------------------------------------------------

### Persistence Unit
------------------------------------------------------------------------

## O que veremos nas próximas aulas

-   Configuração do JPA
-   Entidades e mapeamentos
-   EntityManager na prática
-   JPQL
-   Relacionamentos
-   Integração com DataSource e HikariCP
Conjunto de configurações do JPA (banco, provider, entidades).

------------------------------------------------------------------------

### Mapeamento Objeto-Relacional (ORM)

O JPA faz a ponte entre:

-   Objetos Java
-   Tabelas relacionais

Sem que o desenvolvedor precise escrever SQL para operações básicas.

------------------------------------------------------------------------

## Vantagens do JPA

### 🚀 Produtividade

-   Menos código
-   Mais foco na regra de negócio

### 🔄 Portabilidade

-   Troca de banco com mínimo impacto
-   Troca de provider JPA sem mudar código

### 🧱 Organização

-   Separação clara entre domínio e persistência
-   Código mais limpo e legível

### 🧪 Manutenibilidade

-   Refatorações mais seguras
-   Menos SQL espalhado

------------------------------------------------------------------------

## JPA x JDBC (comparação rápida)

  JDBC               JPA
  ------------------ -------------------
  SQL manual         ORM
  ResultSet          Objetos
  Muito código       Menos código
  Alto acoplamento   Baixo acoplamento

------------------------------------------------------------------------

## JPA não substitui SQL

⚠️ JPA **não elimina** o SQL: - Queries complexas ainda existem -
Conhecimento de banco continua essencial

O JPA **organiza e abstrai**, mas não faz mágica.

------------------------------------------------------------------------

## Onde o JPA é usado?

-   Sistemas corporativos
-   APIs REST
-   Aplicações web
-   Microserviços
-   Spring Boot / Jakarta EE

------------------------------------------------------------------------

## Conclusão

> JPA é uma **camada de abstração** que aumenta produtividade, padroniza
> o acesso a dados e melhora a organização do código, sem substituir o
> conhecimento de banco de dados.


# JPA na Prática: Configuração, Entidades e EntityManager

Este material complementa a introdução ao JPA e apresenta os **primeiros
passos práticos** para utilização da API.

------------------------------------------------------------------------

## 1. Configuração do JPA

A configuração do JPA é feita por meio do arquivo `persistence.xml`, que
define:

-   Qual banco de dados será usado
-   Qual provider JPA (ex: Hibernate)
-   Quais entidades fazem parte do contexto
-   Propriedades de persistência

### Estrutura básica do projeto

``` text
src
 └── main
     └── resources
         └── META-INF
             └── persistence.xml
```

### Exemplo de `persistence.xml`

``` xml
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             version="3.0">

    <persistence-unit name="meuPU" transaction-type="RESOURCE_LOCAL">

        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <class>br.com.exemplo.model.Usuario</class>

        <properties>
            <property name="jakarta.persistence.jdbc.url"
                      value="jdbc:postgresql://localhost:5432/meubanco"/>
            <property name="jakarta.persistence.jdbc.user"
                      value="postgres"/>
            <property name="jakarta.persistence.jdbc.password"
                      value="123"/>

            <property name="hibernate.dialect"
                      value="org.hibernate.dialect.PostgreSQLDialect"/>
            <property name="hibernate.hbm2ddl.auto"
                      value="update"/>
            <property name="hibernate.show_sql"
                      value="true"/>
        </properties>

    </persistence-unit>
</persistence>
```

📌 O nome da *persistence-unit* será usado para criar o
`EntityManagerFactory`.

------------------------------------------------------------------------

## 2. Entidades e Mapeamentos

### O que é uma entidade?

Uma **entidade** é uma classe Java que representa uma tabela do banco de
dados.

Regras básicas: - Deve ser anotada com `@Entity` - Deve possuir uma
chave primária (`@Id`) - Deve ter construtor padrão

------------------------------------------------------------------------

### Exemplo de entidade simples

``` java
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    private String email;

    // construtor padrão
    public Usuario() {}

    // getters e setters
}
```

### Principais anotações de mapeamento

  Anotação            Função
  ------------------- -------------------------------
  `@Entity`           Define a classe como entidade
  `@Table`            Define o nome da tabela
  `@Id`               Chave primária
  `@GeneratedValue`   Geração automática do ID
  `@Column`           Configura coluna
  `@Transient`        Campo não persistido

------------------------------------------------------------------------

### Convenção sobre configuração

> JPA favorece **convenção sobre configuração**.\
> Se nenhuma anotação for definida, o JPA tenta mapear automaticamente.

------------------------------------------------------------------------

## 3. EntityManager na prática

### O que é o EntityManager?

O `EntityManager` é a principal interface do JPA.\
Ele é responsável por:

-   Persistir entidades
-   Buscar dados
-   Atualizar registros
-   Remover entidades
-   Controlar o contexto de persistência

------------------------------------------------------------------------

### Criando o EntityManagerFactory

``` java
EntityManagerFactory emf =
    Persistence.createEntityManagerFactory("meuPU");
```

📌 Deve existir **apenas um** por aplicação.

------------------------------------------------------------------------

### Obtendo um EntityManager

``` java
EntityManager em = emf.createEntityManager();
```

📌 O `EntityManager` **não é thread-safe**\
👉 Um por operação ou requisição.

------------------------------------------------------------------------

### Persistindo dados

``` java
EntityTransaction tx = em.getTransaction();
tx.begin();

Usuario u = new Usuario();
u.setNome("Maria");
u.setEmail("maria@email.com");

em.persist(u);

tx.commit();
em.close();
```
------------------------------------------------------------------------


# Consultas com JPQL (Java Persistence Query Language)

## O que é JPQL?

JPQL (Java Persistence Query Language) é a linguagem de consultas definida
pela especificação JPA.

Ela é **orientada a objetos** e opera sobre:

- Entidades
- Atributos
- Relacionamentos

⚠️ JPQL **não consulta tabelas**, consulta **entidades Java**.

------------------------------------------------------------------------

## JPQL x SQL

| SQL | JPQL |
|----|----|
| Consulta tabelas | Consulta entidades |
| Usa colunas | Usa atributos |
| Depende do banco | Independente do banco |

Exemplo:

```sql
SELECT * FROM usuarios;
```

```jpql
SELECT u FROM Usuario u;
```

------------------------------------------------------------------------

## Estrutura básica de uma consulta JPQL

```java
SELECT alias FROM Entidade alias
```

Exemplo:

```java
SELECT u FROM Usuario u
```

------------------------------------------------------------------------

## Executando uma consulta JPQL

```java
List<Usuario> usuarios =
    em.createQuery("SELECT u FROM Usuario u", Usuario.class)
      .getResultList();
```

------------------------------------------------------------------------

## Cláusula WHERE

```java
SELECT u FROM Usuario u WHERE u.nome = 'Maria'
```

### Usando parâmetros (boa prática)

```java
SELECT u FROM Usuario u WHERE u.nome = :nome
```

```java
em.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nome", Usuario.class)
  .setParameter("nome", "Maria")
  .getResultList();
```

------------------------------------------------------------------------

## LIKE em JPQL

O operador `LIKE` funciona de forma semelhante ao SQL.

```java
SELECT u FROM Usuario u WHERE u.nome LIKE 'Ma%'
```

### LIKE com parâmetro

```java
SELECT u FROM Usuario u WHERE u.nome LIKE :nome
```

```java
.setParameter("nome", "Ma%")
```

### Padrões comuns

| Objetivo | Exemplo |
|-------|-------|
| Começa com | `"Jo%"` |
| Termina com | `"%silva"` |
| Contém | `"%ana%"` |

------------------------------------------------------------------------

## LIKE sem diferenciar maiúsculas/minúsculas

JPQL não possui `ILIKE`.

Solução portátil:

```java
SELECT u FROM Usuario u
WHERE LOWER(u.nome) LIKE LOWER(:nome)
```

------------------------------------------------------------------------

## Funções agregadas

JPQL suporta funções como:

- COUNT
- AVG
- SUM
- MIN
- MAX

Exemplo:

```java
SELECT COUNT(u) FROM Usuario u
```

Retorno: `Long`

------------------------------------------------------------------------

## getResultList x getSingleResult

```java
getResultList();   // Retorna lista (pode ser vazia)
getSingleResult(); // Retorna um único resultado
```

⚠️ `getSingleResult()` pode lançar:
- `NoResultException`
- `NonUniqueResultException`

------------------------------------------------------------------------

## Erros comuns em JPQL

- Usar nome da tabela em vez da entidade
- Usar nome da coluna em vez do atributo
- Esquecer alias
- Esperar entidade ao usar projeções

------------------------------------------------------------------------

## Quando usar JPQL?

- Consultas dinâmicas
- Consultas orientadas a domínio
- Independência de banco

------------------------------------------------------------------------

## Conclusão

> JPQL permite consultar dados usando o modelo orientado a objetos,
> mantendo o código limpo, portátil e alinhado ao domínio da aplicação.
