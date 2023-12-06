package DGU;

public class Grade_2019111679_005_1 {
	
	// 같은 패키지 - 같은 클래스
	private static String privateAccess = "This is private";
	public static String publicAccess = "This is public";
	protected static String protectedAccess = "This is protected";
	static String defaultAccess = "This is default";
	
	public static void main(String[] args) {
		System.out.println(privateAccess);
		System.out.println(publicAccess);
		System.out.println(protectedAccess);
		System.out.println(defaultAccess);
	}

}
