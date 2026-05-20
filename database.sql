-- ==========================================
-- 1. LIMPEZA DO AMBIENTE (OPCIONAL)
-- Cuidado: Isto apagará os dados atuais para recriar do zero
-- ==========================================
DROP TABLE IF EXISTS favoritos CASCADE;
DROP TABLE IF EXISTS curtidas CASCADE;
DROP TABLE IF EXISTS videos CASCADE;
DROP TABLE IF EXISTS usuarios CASCADE;

-- ==========================================
-- 2. CRIAÇÃO DAS TABELAS PRINCIPAIS
-- ==========================================

-- Tabela de Utilizadores
CREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(50) NOT NULL
);

-- Tabela de Vídeos (Inclui a coluna ANO)
CREATE TABLE videos (
    id_video SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    ano INT NOT NULL
);

-- ==========================================
-- 3. CRIAÇÃO DAS TABELAS DE RELACIONAMENTO
-- ==========================================

-- Tabela de Favoritos (Muitos-para-Muitos)
CREATE TABLE favoritos (
    id_usuario INT REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    id_video INT REFERENCES videos(id_video) ON DELETE CASCADE,
    PRIMARY KEY (id_usuario, id_video)
);

-- Tabela de Curtidas (Likes)
CREATE TABLE curtidas (
    id_usuario INT REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    id_video INT REFERENCES videos(id_video) ON DELETE CASCADE,
    PRIMARY KEY (id_usuario, id_video)
);

-- ==========================================
-- 4. POVOAMENTO INICIAL (DADOS DE TESTE)
-- ==========================================

INSERT INTO videos (titulo, genero, ano) VALUES 
('Interestelar', 'Ficção Científica', 2014),
('Batman: O Cavaleiro das Trevas', 'Ação', 2008),
('O Poderoso Chefão', 'Drama', 1972),
('Matrix', 'Ficção Científica', 1999),
('Vingadores: Ultimato', 'Ação', 2019),
('Parasita', 'Suspense', 2019),
('O Rei Leão', 'Animação', 1994),
('A Origem', 'Ficção Científica', 2010),
('Pulp Fiction', 'Crime', 1994),
('Gladiador', 'Ação', 2000),
('Coringa', 'Drama', 2019),
('Duna', 'Ficção Científica', 2021);