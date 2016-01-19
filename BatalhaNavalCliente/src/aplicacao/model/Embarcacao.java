package aplicacao.model;

public interface Embarcacao {
	
	/* Retorna true sse todas as células que compõem a embarcação estiverem afundadas.
	 * Caso contrário retorna false. */
	public boolean afundada ();
	/* Retorna a posição na linha do tabuleiro em que se encontra a primeira célula da
	 * embarcação. */
	public int posX ();
	/* Retorna a posição da coluna do tabuleiro em que se encontra a primeira célula da
	 * embarcação. */
	public int posY ();

}
