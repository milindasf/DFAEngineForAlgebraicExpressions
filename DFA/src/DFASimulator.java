public class DFASimulator {

	public static void main(String[] args) {
		DFAEngine arithmeticExpDetector = DFAEngine.initializeDFA();
		System.out.println("+++Deterministic Finite Automata to accept Arithmetic Expressions+++");
		System.out.println("Authors:");
		System.out.println("Tharindu Rusira(tharindurusira@gmail.com),Milinda Fernando(milindasf@gmail.com)");
		System.out.println("Department of Computer Sceince and Engineering\nUniversity of Moratuwa\nSri Lanka\n\n\n");
		String input;
		
		if(args.length == 1){
			input= args[0];
			if(arithmeticExpDetector.CanRecognize(input)){
				System.out.println(input+ " is accepted by the DFA\n");
			}else{
				System.out.println(input+ " is rejected by the DFA\n");
			}
		}else{
			System.out.println("Invalid input. Please try again\n");
		}
	}

}
