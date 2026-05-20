# Projeto_PrimeFEI

Projeto para a aula de programação orientada a objeto 

Disciplina: ARQUITETURA DE SOFTWARE E PROGRAMAÇÃO ORIENTADA A OBJETOS
Desenvolvedor: Lucas Melo Priolli 

#Índice
 
1.	Introdução
2.	Arquitetura do Sistema (MVC)
3.	Modelo de Dados e Persistência
4.	Funcionalidades Implementadas
5.	Segurança e Gestão de Configurações
6.	Instruções de Instalação e Utilização
7.	Conclusão

   
# 1. Introdução
Este relatório documenta o desenvolvimento de uma aplicação desktop robusta para a gestão e interação com conteúdos de vídeo (filmes/séries). O foco do projeto foi a criação de uma experiência personalizada, permitindo que utilizadores autenticados possam navegar num catálogo, pesquisar por critérios específicos e gerir as suas preferências através de sistemas de "Favoritos" e "Curtidas". 
A aplicação foi desenvolvida em Java, utilizando a biblioteca Swing para a interface gráfica e o PostgreSQL como sistema de gestão de base de dados relacional. 

#2. Arquitetura do Sistema (MVC)
 
Para garantir a escalabilidade e a manutenção do código, foi adotado o padrão de projeto Model-View-Controller (MVC). Esta separação permite que a lógica de acesso aos dados seja independente da interface visual. 
•	Model: Responsável pela representação dos dados e comunicação com o banco (DAOs).
•	View: Composta por JFrames Swing que gerem a interação com o utilizador.
•	Controller: Atua como o intermediário, processando as ações da View e coordenando os fluxos com os DAOs.




 
# 3. Modelo de Dados e Persistência
 
O esquema da base de dados foi desenhado para suportar relacionamentos muitos-para-muitos entre utilizadores e vídeos. Abaixo, detalham-se as tabelas principais: 
Tabela	Descrição
usuarios	Armazena credenciais (nome, usuário, senha,IDs).
videos	Catálogo com títulos, géneros e IDs.
favoritos	Relaciona IDs de usuários a IDs de vídeos marcados como preferidos.
curtidas	Relaciona IDs de usuários a IDs de vídeos gerando as interações de "Like" dos utilizadores.

# 4. Funcionalidades Implementadas

4.1. Autenticação e Sessão
O sistema implementa uma gestão de sessão no Controller. Ao realizar o login, o ID do utilizador é capturado e mantido em memória, permitindo que as ações de inserção no banco de dados sejam automaticamente vinculadas ao perfil correto. 

4.2. Sistema de Favoritos e Curtidas
Diferente de implementações simplistas, o sistema utiliza botões de ação direta ("Curtir" e "Descurtir") e uma visualização em lista (JList) para os favoritos. A lógica de exclusão foi implementada para garantir que o utilizador tenha controlo total sobre os seus dados salvos. 



# 5. Segurança e Gestão de Configurações
 
Um dos pontos críticos abordados foi a segurança das credenciais da base de dados. Implementou-se a utilização de arquivos de configuração externos (.env), que são ignorados pelo controlo de versão (Git) através da pasta .gitignore.
Isso evita a exposição de senhas sensíveis no repositório público do GitHub. 

6. Instruções de Instalação e Utilização
 
Para executar o projeto localmente, siga os passos abaixo: 
1.	Certifique-se de ter o PostgreSQL instalado e o driver JDBC configurado no projeto.
2.	Execute o script SQL presente em database.sql para criar as tabelas necessárias.
3.	Configure as credenciais no arquivo config.properties na raiz do projeto.
4.	Inicie a aplicação através da classe principal TelaInicial ou LoginJFrame.
7. Conclusão
O desenvolvimento deste projeto permitiu a consolidação de conhecimentos avançados em Java e bases de dados relacionais. A aplicação do padrão MVC provou ser essencial para resolver problemas de conectividade e organização de código que surgiram durante o ciclo de desenvolvimento. O resultado é um software funcional, seguro e documentado seguindo as melhores práticas da engenharia de software.



Link do vídeo explicando: https://www.youtube.com/watch?v=-C176-zeaOI
