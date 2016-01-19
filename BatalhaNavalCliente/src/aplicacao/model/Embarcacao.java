package aplicacao.model;

public interface Embarcacao {
	
	/* Retorna true sse todas as c�lulas que comp�em a embarca��o estiverem afundadas.
	 * Caso contr�rio retorna false. */
	public boolean afundada ();
	/* Retorna a posi��o na linha do tabuleiro em que se encontra a primeira c�lula da
	 * embarca��o. */
	public int posX ();
	/* Retorna a posi��o da coluna do tabuleiro em que se encontra a primeira c�lula da
	 * embarca��o. */
	public int posY ();

}
