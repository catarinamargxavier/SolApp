# SolApp
Individual project (mobile applications) - Introdução à Computação Móvel - Engenharia Informática - Universidade de Aveiro


## Funcionalidade implementada

A SolApp é uma aplicação para dispositivos Android que através da API do IPMA apresenta dados sobre o tempo de várias regiões. O utilizador escolhe um local como 'Aveiro' e de seguida são apresentadas as previsões para os próximos 5 dias. Cada previsão é composta por temperatura máxima, temperatura mínima, probabilidade de chover, estado do vento e o info geral (por exemplo, 'céu pouco nublado').


## Arquitetura

	Activity/Fragment
		|
		|			
	ViewModel (com LiveData)
		|
		|
	----Repository---
	|		|
	|		|
Model (com Room)  Remote Data Source (com Retrofit)
(base de dados	  (webservice)	 
SQLite)		

### Activity/Fragment:
- MainActivity (escolha do local)
- WeatherActivity (apresentação dos dados)
- MyRecyclerViewAdapter (configuração de cada row do recycler view que pertence à WeatherActivity)

### ViewModel:
- PrevisionViewModel (ligação do repositório às atividades)

### Repository:
- MyRepository (componente que liga à base de dados e ao webService e escolhe quando usar qual e quando fazer novas transferências do repositório remoto)

### Model:
- MyDatabase (definição da base de dados)
- EntitiesDao (interface que define todos os métodos de interação com a base de dados)
- DateConverter (simples convertor de datas para utilização na base de dados)
- Local (objeto associado a tabela da db)
- Prevision (objeto associado a tabela da db)
- Weather (objeto associado a tabela da db)
- WindSpeed (objeto associado a tabela da db)

### Remote Data Source:
- Endpoint Interface (definição dos caminhos da API)
- Local (objeto de manipulação de dados)
- ObjectLocal (objeto de manipulação de dados)
- Prevision (objeto de manipulação de dados)
- ObjectPrevision (objeto de manipulação de dados)
- Weather (objeto de manipulação de dados)
- ObjectWeather (objeto de manipulação de dados)
- WindSpeed (objeto de manipulação de dados)
- ObjectWindSpeed (objeto de manipulação de dados)

Para consumo da API foi utilizado GSON e Retrofit. Para manipulação de tarefas que devem ser executadas fora da MainThread foi utilizado Executor.


## Limitações

Não consegui garantir a ordem correta dos dias na apresentação das previsões no RecyclerView. Gostava de ter implementado um Service para ir buscar atualizações de 15 em 15 min. Da maneira que fiz ele só vai buscar atualizações quando a App abre ou se o utilizador escolhe um local e a informação na base de dados tiver mais de 15 min.


## Cuidados particulares

Não é preciso cuidados particulares a correr a aplicação a não ser uma ligação à internet pelo menos na primeira vez que se correr a aplicação.
