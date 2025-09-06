// Importamos o nosso arquivo de estilos para que a página possa usá-lo.
import styles from './Home.module.css';

// Função que será executada no servidor para buscar os dados da nossa API.
async function getArticles() {
    try {
        console.log("PASSO 1: Tentando buscar artigos em http://localhost:8080/api/news/articles");
        // A chamada para o backend. { cache: 'no-store' } garante que os dados sejam sempre frescos.
        const res = await fetch('http://localhost:8080/api/news/articles', { cache: 'no-store' });

        console.log("PASSO 2: Resposta recebida do backend. Status:", res.status);
        if (!res.ok) {
            console.error('Resposta do backend não foi OK:', res.status, res.statusText);
            throw new Error('Falha ao buscar notícias do backend');
        }

        const data = await res.json();
        console.log("PASSO 3: Dados convertidos para JSON. Artigos recebidos:", data.length);
        return data;

    } catch (error) {
        console.error("ERRO na função getArticles:", error.message);
        // Retorna uma lista vazia para a página não quebrar se o backend estiver offline.
        return [];
    }
}

// Este é o componente que renderiza nossa página.
export default async function Home() {
    const articles = await getArticles();

    return (
        <main className={styles.mainContainer}>
            <div className={styles.contentWrapper}>
                <h1 className={styles.title}>Central News Hub</h1>

                {/* Verificamos se há artigos para exibir. Se não, mostramos uma mensagem. */}
                {articles.length === 0 ? (
                    <p style={{ textAlign: 'center' }}>Nenhum artigo encontrado. Tente buscar novas notícias ou verifique se o backend está rodando.</p>
                ) : (
                    <div className={styles.articlesGrid}>
                        {/* Para cada artigo na lista, criamos um bloco de exibição. */}
                        {articles.map((article) => (
                            <div key={article.id} className={styles.card}>
                                <a href={article.url || '#'} target="_blank" rel="noopener noreferrer">

                                    {/* Só exibe a tag <img> se a URL da imagem não for nula/vazia */}
                                    {article.urlToImage && (
                                        <img src={article.urlToImage} alt={article.title || 'Imagem da notícia'} className={styles.cardImage} />
                                    )}

                                    {/* Usamos || para fornecer um valor padrão caso o campo seja nulo */}
                                    <h2 className={styles.cardTitle}>{article.title || 'Título não disponível'}</h2>
                                    <p className={styles.cardDescription}>{article.description || 'Descrição não disponível.'}</p>

                                    <div className={styles.cardMeta}>
                                        {/* Usamos 'article.source?.name' para acessar 'name' de forma segura.
                        Se 'source' for nulo, ele não tentará ler 'name' e não dará erro. */}
                                        <span>{article.source?.name || 'Fonte desconhecida'}</span>

                                        {/* Verificamos se a data existe antes de tentar formatá-la */}
                                        <span>
                      {article.publishedAt
                          ? new Date(article.publishedAt).toLocaleDateString('pt-BR', { day: '2-digit', month: 'long', year: 'numeric' })
                          : 'Data indisponível'}
                    </span>
                                    </div>
                                </a>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </main>
    );
}