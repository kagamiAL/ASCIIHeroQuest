package asciiHeroQuest;

public class Utilities {
  public static void print(String mainArg, boolean printNewLine){
    if (printNewLine){
      System.out.println(mainArg);
      return;
    }
    System.out.print(mainArg);
  }

  public static void skipLines(int numLines){
    for (int x = 0; x < numLines; x++){
      print("", true);
    }
  }

  public static void clearOutput(){
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }

  public static void wait(int milliSeconds){
    try{
      Thread.sleep(milliSeconds);
    }
    catch (Exception e){
        Thread.currentThread().interrupt();
    }
    
  }

  public static String safeNextLine() {
	  String stringReturn	= null;
	  boolean passedSafely	= false;
	  
	  while (!passedSafely) {
		  passedSafely		= true;
		  try {
			  stringReturn	= TransferredData.USER_INPUT.nextLine();
			  if (stringReturn.isBlank()) {
				  throw new SecurityException("String entered has 0 letters");
			  }
		  } catch(Exception e) {
			  passedSafely	= false;
			  Utilities.print("Enter a valid string!", true);
		  }
	  }
	  
	  return stringReturn;	
  }
  
}