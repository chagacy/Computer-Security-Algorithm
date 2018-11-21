package Encryption;

public class Encryption {

	public static void main(String[] args) {
		
		Encryption e  = new Encryption();
		
		String example = "This is my encryption.";
		
		example = example.replaceAll("[^A-Za-z]+", "").toLowerCase();
		
		example = e.caesarEncrypt(example,2);
		
		System.out.println(example);
		
		example = e.railFenceEncrypt(example, 3);

		System.out.println(example);
		
		example = e.railFenceDecrypt(example, 3);

		System.out.println(example);
		
		example = e.caesarDecrypt(example, 2);

		System.out.println(example);
		
		example = e.xorEncryptDecrypt(example, "password");
		
		System.out.println(example);
		
		example = e.xorEncryptDecrypt(example, "password");
		
		System.out.println(example);
		
		
	}
	
	public String encrypt(String plaintext, int caesarKey, int railFenceKey, String xorKey){
		
		plaintext = plaintext.replaceAll("[^A-Za-z]+", "").toLowerCase();
		
		String ciphertext = caesarEncrypt(plaintext, caesarKey);
		
		ciphertext = railFenceEncrypt(ciphertext, railFenceKey);
		
		ciphertext = xorEncryptDecrypt(plaintext, xorKey);
		
		return ciphertext;
	}
	

	public String decrypt(String ciphertext, int caesarKey, int railFenceKey, String xorKey){
		
		String plaintext = xorEncryptDecrypt(ciphertext, xorKey);
		
		plaintext = railFenceDecrypt(plaintext, railFenceKey);
		
		plaintext = caesarDecrypt(plaintext, caesarKey);
		
		return ciphertext;
	}
	
	private String caesarEncrypt(String plaintext, int key) {

		String ciphertext = "";
		
		for (int i=0; i < plaintext.length(); i++) {
			char letter = (char) ((plaintext.charAt(i) + (key)) % 'z');

			if (letter <97) {
				letter += 96;
			}

			ciphertext += letter;
		}
		
		return ciphertext;
	}


	private String caesarDecrypt(String ciphertext, int key) {
		String plaintext = caesarEncrypt(ciphertext, 26-key);
		return plaintext;
	}


	private String railFenceEncrypt(String plaintext, int key) {

		int row=key;
		int column=plaintext.length()/key;
		char railMatrix[][]=new char[row][column];
		int count=0;
		   
		String cipherText="";
		  
		for(int i=0;i< column;i++)
		{
			for(int j=0;j< row;j++)
			{
				if(count!=plaintext.length()){
					railMatrix[j][i]=plaintext.charAt(count);
					count++;
				}
				else
					railMatrix[j][i]='X';
			}
		}
		for(int i=0;i< row;i++)
		{
			for(int j=0;j< column;j++)
			{
				cipherText+=railMatrix[i][j];
			}
		}
		return cipherText;
		  
	}

	private String railFenceDecrypt(String ciphertext, int key) {
		int row=key;
		int column=ciphertext.length()/key;
		char railMatrix[][]=new char[row][column];
		int count=0;
		   
		String plaintext="";
		  
		for(int i=0;i< row;i++)
		{
			for(int j=0;j< column;j++)
			{
				if(count!=ciphertext.length()){
					railMatrix[i][j]=ciphertext.charAt(count);
					count++;
				}
			}
		}
		for(int i=0;i< column;i++)
		{
			for(int j=0;j< row;j++)
			{
				plaintext+=railMatrix[j][i];
			}
		}
		return plaintext;
	}
	
	private String xorEncryptDecrypt(String plaintext, String key) {
		
		StringBuilder c = new StringBuilder();
		
		for (int i=0; i<plaintext.length(); i++){
			c.append((char) (plaintext.charAt(i) ^ key.charAt(i % key.length())));
		}
		
		return c.toString();
	}	
	
}
