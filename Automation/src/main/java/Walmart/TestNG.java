//main method must not be there
// any code in testing class must be inside method
/* all the methods must be enclosed with annotations 
 	@beforesuit, @beforemethod, @aftersuit, @aftermethod, @test
*/ 
package Walmart;


public class TestNG {
	
	public void openbrowser(){
		System.out.print("open method");
	}
	
	public void validcase(){
		System.out.print("Enter 4-10 characters");
	}
	
public void invalidcase(){
	System.out.print("write less than 2 characters");
}


public void closebrowser(){
	System.out.print("close method");
}

}