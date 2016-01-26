package aplicacao.model;

public class Player {
	private static int geradorIDs = 0;
	public int id;
	public String nickname;

	public Player () {
		id = ++geradorIDs;
		nickname = "Gen. Anonymous";
	}

	public Player (String nickname) {
		id = ++geradorIDs;
		this.nickname = nickname;
	}
}
