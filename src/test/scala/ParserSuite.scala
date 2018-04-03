import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/28/17.
  */
class ParserSuite  extends FunSuite with TestParser {

  test("Program contains declarations") {
		val input = """
			int a;
			void foo(){
			}
			float a;
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,101))
	}
	
	test("Program contains declarations 2") {
		val input = """
			int a,b,c;
			string a[1];
			int main() {}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,102))
	}
	
	test("Program contains declarations 3") {
		val input = """
			boolean a;
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,103))
	}
	
	test("Program contains declarations 4") {
		val input = """
			string _f(){}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,104))
	}
	
	test("Variable declarations") {
		val input = """
			boolean a,b[0],_aa;
			int x;
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,105))
	}
	
	test("Variable declarations 2") {
		val input = """
			void f() {
				boolean a,b[0],_aa;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,106))
	}
	
	test("Variable declarations 3") {
		val input = """
			void f() {
				{
					boolean a,b[0],_aa;
				}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,107))
	}
	
	test("Variable declarations 4") {
		val input = """
			void f() {
				boolean a,b[0],_aa;
				string a,b[0];
				{
					boolean a,b[0],_aa;
				}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,108))
	}
	
	test("Function declarations") {
		val input = """
			void f() {
				
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,109))
	}
	
	test("Function declarations 2") {
		val input = """
			void f(int a, float b[]) {
				
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,110))
	}
	
	test("Function declarations 3") {
		val input = """
			void f(int a, float b[]) {
				
			}
			void k(boolean b[]) {
				
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,111))
	}
	
	test("Function declarations 4") {
		val input = """
			void f(int a, float b[]) {
				
			}
			string k() {
				
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,112))
	}
	
	test("Nested function") {
		val input = """
			void f(int a, float b[]) {
				string k() {
				
				}
			}
		"""
		val expect = "Error on line 3 col 13: ("
		assert(checkRec(input,expect,113))
	}
	
	test("Primitive type") {
		val input = """
			boolean a;
			int b;
			string c;
			float d;
			boolean f() {}
			string f() {}
			float f(){}
			int f() {}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,114))
	}
	
	test("Void type") {
		val input = """
			void f() {
			
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,115))
	}

  test("PARAMETER error") {
		val input = """
			void f(int a[3]) {
				
			}
		"""
		val expect = "Error on line 2 col 17: 3"
		assert(checkRec(input,expect,116))
	}
	
	test("Void type error") {
		val input = """
			void a;
		"""
		val expect = "Error on line 2 col 10: ;"
		assert(checkRec(input,expect,117))
	}
	
	test("Array type") {
		val input = """
			int a[1];
			string b[2];
			float c[3];
			boolean d[4];
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,118))
	}
	
	test("Array type int error") {
		val input = """
			int a[1.1];
		"""
		val expect = "Error on line 2 col 10: 1.1"
		assert(checkRec(input,expect,119))
	}
	
	test("Array pointer type") {
		val input = """
			int[] f(float a[], string c[]){}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,120))
	}
	
	test("Array pointer type error") {
		val input = """
			int[] a;
		"""
		val expect = "Error on line 2 col 11: ;"
		assert(checkRec(input,expect,121))
	}
	
	test("Array pointer type error 2") {
		val input = """
			int a[];
		"""
		val expect = "Error on line 2 col 10: ]"
		assert(checkRec(input,expect,122))
	}
	
	test("Variable initialization") {
		val input = """
			int a = 1;
		"""
		val expect = "Error on line 2 col 10: ="
		assert(checkRec(input,expect,123))
	}
	
	test("Variable initialization 2") {
		val input = """
			boolean a[2] = {true, false};
		"""
		val expect = "Error on line 2 col 17: ="
		assert(checkRec(input,expect,124))
	}
	
	test("Expression: OR") {
		val input = """
			void f() {
				a = a || b || b;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,125))
	}
	
	test("Expression: AND") {
		val input = """
			void f() {
				a = a && b && c;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,126))
	}
	
	test("Expression: GREATER") {
		val input = """
			void f() {
				a = a >= b;
				a = a > b;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,127))
	}
	
	test("Expression: GREATER 2") {
		val input = """
			void f() {
				a = (a >= b) >= b;
				a = (a > b) > c;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,128))
	}
	
	test("Expression: LESS") {
		val input = """
			void f() {
				a = a <= b;
				a = a < b;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,129))
	}
	
	test("Expression: LESS 2") {
		val input = """
			void f() {
				a = (a <= b) <= c;
				a = (a < b) <= c;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,130))
	}
	
	test("Expression: GREATER ERROR") {
		val input = """
			void f() {
				a = a >= b >= c;
			}
		"""
		val expect = "Error on line 3 col 16: >="
		assert(checkRec(input,expect,131))
	}
	
	test("Expression: ADD SUBTRACT") {
		val input = """
			void f() {
				a = a + b - c;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,132))
	}

	test("Expression: MUL DIVIDE") {
		val input = """
			void f() {
				a = a * b / c % d;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,133))
	}
	
	test("Expression: MIXED") {
		val input = """
			void f() {
				a = a * b / c % d  + f - h > b || c && m == v || a != a;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,134))
	}
	
	test("Expression: UNARY") {
		val input = """
			void f() {
				a = !a;
				a = -a;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,135))
	}
	
	test("Expression: EQUAL") {
		val input = """
			void f() {
				a = a == a;
				a = a != a;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,136))
	}
	
	test("Expression: EQUAL ERROR") {
		val input = """
			void f() {
				a = a == a != a;
			}
		"""
		val expect = "Error on line 3 col 16: !="
		assert(checkRec(input,expect,137))
	}
	
	test("Expression: EQUAL 2") {
		val input = """
			void f() {
				a = (a == a) != a;
				a = (a != a) == a;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,138))
	}
	
	test("Expression: INDEX") {
		val input = """
			void f() {
				a = a[1];
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,139))
	}
	
	test("Expression: INDEX 2") {
		val input = """
			void f() {
				a = a[a + b];
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,140))
	}
	
	test("Expression: INDEX 3") {
		val input = """
			void f() {
				a[a + b] = a[a + b];
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,141))
	}
	
	test("Expression: INDEX 4") {
		val input = """
			void f() {
				foo(2)[3+x] = a[b[2]] +3;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,142))
	}
	
	test("Expression: INVOCATION") {
		val input = """
			void f() {
				a = f(1,2,3);
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,143))
	}
	
	test("Expression: INVOCATION 2") {
		val input = """
			void f() {
				a = f();
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,144))
	}
	
	test("Expression: INVOCATION 4") {
		val input = """
			void f(int a) {
				return;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,145))
	}
	
	test("Expression: INVOCATION 5") {
		val input = """
			void f(int a) {
				return a + b + 1.2;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,146))
	}
	
	test("Expression: INVOCATION 6") {
		val input = """
			void f(int a) {
				return f();
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,147))
	}
	
	test("Expression: INVOCATION 7") {
		val input = """
			void f(int a) {
				a = f(a[1], b,c);
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,148))
	}
	
	test("Expression: INVOCATION 8") {
		val input = """
			void f(int a) {
				a = f(a[1], b,c)[a + b];
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,149))
	}
  	test("a simple program") {
    	val input = "int main () {}"
    	val expect = "sucessful"
    	assert(checkRec(input,expect,150))
  	}

	test("Statement: If-noelse") {
		val input = """
			void f(int a) {
				if (a + b) 
					if (c) {}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,151))
	}
	
	test("Statement: If-else") {
		val input = """
			void f(int a) {
				if (a || b) {}
				else if (true) {}
				else {}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,152))
	}
	
	test("Statement: If-else 2") {
		val input = """
			void f(int a) {
				if (a || b) break;
				else if (true) break;
				else break;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,153))
	}
	
	test("Statement: do while") {
		val input = """
			void f(int a) {
				do 
					break;
					{}
					{}
				while (true);
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,154))
	}
	
	test("Statement: do while 2") {
		val input = """
			void f(int a) {
				do 
					break;
				while (a == b);
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,155))
	}
	
	
	test("Statement: for") {
		val input = """
			void f(int a) {
				for (a; b; c) {}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,156))
	}
	
	test("Statement: for 2") {
		val input = """
			void f(int a) {
				for (i = 1;  i <= 10; i+1) {
					print(i);
				}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,157))
	}
	
	test("Statement: break") {
		val input = """
			void f(int a) {
				break;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,158))
	}
	
	test("Statement: break 2") {
		val input = """
			void f(int a) {
				do { break; }
				while(false);
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,159))
	}
	
	test("Statement: continue") {
		val input = """
			void f(int a) {
				do { continue; }
				while(false);
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,160))
	}
	
	test("Statement: continue 2") {
		val input = """
			void f(int a) {
				continue;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,161))
	}
	
	test("Statement: return") {
		val input = """
			void f(int a) {
				return;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,162))
	}
	
	test("Statement: return 2") {
		val input = """
			void f(int a) {
				return 1;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,163))
	}
	
	test("Statement: return 3") {
		val input = """
			void f(int a) {
				{
					return 1;
				}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,164))
	}
	
	test("Statement: expression") {
		val input = """
			void f(int a) {
				a;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,165))
	}
	
	test("Statement: expression 1") {
		val input = """
			void f(int a) {
				a == b && c;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,166))
	}
	
	test("Statement: expression 2") {
		val input = """
			void f(int a) {
				foo(a,a + b, c);
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,167))
	}
	
	test("Statement: expression 3") {
		val input = """
			void f(int a) {
				a + b - c * dj;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,168))
	}
	
	test("Statement: block") {
		val input = """
			void f(int a) {
				{
					float a;
					float b;
					
					a + b;
				}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,169))
	}
	
	test("Statement: block 2") {
		val input = """
			void f(int a) {
				{
					float a;
					
					
					a + b;
					
					float b;
				}
			}
		"""
		val expect = "Error on line 9 col 6: float"
		assert(checkRec(input,expect,170))
	}
	
	test("Statement: block 3") {
		val input = """
			void f(int a) {
			
				{
					a + b;
					
					float b;
				}
			}
		"""
		val expect = "Error on line 7 col 6: float"
		assert(checkRec(input,expect,171))
	}
	
	test("Statement: block 4") {
		val input = """
			void f(int a) {
			
				{
					a + b;
					{
						int a;
					}
				}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,172))
	}
	
	test("Stament: mixed") {
		val input = """
			int i;
			
			void f() {
				
			} 
			
			void main() {
				f(1);
				break;
				if (a == b) return 1;
				else return;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,173))
	}
	
	test("mixed") {
		val input = """
			int a;
			void main(){
				int b[100];
				b = 5;
			}	
			
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,174))
	}
	
	test("mixed 2") {
		val input = """
			int b;
			int add(int s) {
				s = b + "123";
			} 
			
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,175))
	}
	
	test("mixed 3") {
		val input = """
			int a;
			string b;
			void b(){
				b="kha";	
			}
			int foo(int a, int c){
				for (i = 1; i <=4; i + 1){
					if(i%2==0) {
						print(i);			
					}	
				} 
				return a+c;	
			}
			float c,d,e;
			string kha(string b){
				int x,y,z;		
				a=b+c;
				t=x+y/z*y%d;
				foo(foo(x,y),z);
			}

			
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,176))
	}
	
	test("mixed 4") {
		val input = """
			boolean a,b;
			int c;
			void main(){
				c = a % b;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,177))
	}
	
	test("mixed 5") {
		val input = """
			int factorial(int n){
				return 1;
			}
			void main(){
				boolean x;
				int a[5];
				float b[4];
				a[0]=333*4%5;
			}

		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,178))
	}
	
	test("mixed 6") {
		val input = """
			int b;
			string c;
			int d;
			int foo(int a, int b){
				e = a + b;
				foo(e);
				return e;
			}


		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,179))
	}
	
	test("mixed 7") {
		val input = """
			void func(string a, string b, float c){
				int i;
				for (i = 0; i < 10; i + 1){
					if (a + b) { 
						b = a;
					}
				}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,180))
	}
	
	test("mixed 8") {
		val input = """
			void func(string a, string b, float c){
				int i;
				for (i = 0; i < 10; i + 1){
					if a + b { 
						b = a;
					}
				}
			}
		"""
		val expect = "Error on line 5 col 9: a"
		assert(checkRec(input,expect,181))
	}
	
	test("mixed 9") {
		val input = """
			void main(){
	
				int Test_9;
				
				Test_9 = 1;
				
			}

		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,182))
	}
	
	test("mixed 10") {
		val input = """
			void main(){
				int a[5];
				float b[5];
				void result()
				{
					a[2] = b[2];
				}
			}

		"""
		val expect = "Error on line 5 col 5: void"
		assert(checkRec(input,expect,183))
	}
	
	test("mixed 11") {
		val input = """
			int a;
			string x;
			void main(int b){
				x = 1;
				get();	
			}

		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,184))
	}
	
	test("mixed 12") {
		val input = """
			float i;
			void g() {
				for (i = 1; i <= 2; i + 1) {
					if (1)  
						i = 1;
				}
			}


		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,185))
	}
	
	test("mixed 13") {
		val input = """
			void swap(int a[], int b[]){
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,186))
	}
	
	test("mixed 14") {
		val input = """
			void move(){
				int a,b,c;
				float x,y,z;
				
				c = a/b;
				c = a/b;
				c = a/y;
				c = a+y;
				z = x/y;
				z = x+a;
				z = x/y;
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,187))
	}
	
	test("mixed 15") {
		val input = """
			void calc(){
				int a;
				int b;
				b=galaxy();
			}
			int galaxy(int r){
				return 100;
			}
		}
		"""
		val expect = "Error on line 10 col 3: }"
		assert(checkRec(input,expect,188))
	}
	
	test("mixed 16") {
		val input = """
			int i;
			void g() {
				f(3);
				for (i = 1; i <=2; i + 1) {
					if (i < 2)  
						i = 1;
					else i = i + 5;
				}
			}
			void g(){}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,189))
	}
	
	test("mixed 17") {
		val input = """
			int c;

			void main(int x, float y, float z)
			{
				string a;
				float b;

				{
					int y;
					y = "xxx";
				}
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,190))
	}

	test("Statement: do while error") {
		val input = """
			void f(int a) {
				do 
				while (a == b);
			}
		"""
		val expect = "Error on line 4 col 5: while"
		assert(checkRec(input,expect,191))
	}

	test("Statement before declare") {
		val input = """
			void f(int a) {
				do { break; }
				while(false);
				int a;
			}
		"""
		val expect = "Error on line 5 col 5: int"
		assert(checkRec(input,expect,192))
	}

  	test("more complex program") {
    	val input ="""int main () {
		putIntuLn(4542);
  	}"""
    	val expect ="sucessful"
    	assert(checkRec(input,expect,193))
 	}
  	test("wrong program"){
    	val input = "} int main {"
    	val expect = "Error on line 1 col 1: }"
    	assert(checkRec(input,expect,194))
  	}
  	test("Function and variable declarations 1") {
		val input = """
      	int a;
      	int b[3];
			void f(int a, float b[]) {
				
			}
			string k() {
				
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,195))
	}
  	test("Function and variable declarations 2") {
		val input = """
      	int a;
			void f(int a, float b[]) {
				
			}
      	int b[2];
			string k() {
				
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,196))
	}
  	test("Function and variable declarations 3") {
		val input = """
			void f(int a) {
				
			}
      	int b[2],d,k;
      	string f;
			float k(int a, int b) {
				
			}
		"""
		val expect = "sucessful"
		assert(checkRec(input,expect,197))
	}
  test("Function and variable declarations error 1") {
		val input = """
			void f(int a) {
				
			}
      	int b[2],d,k;
      	string f;
			float k(int a, int b) {
				
			}
      	int main(string [] a, int b){}
		"""
		val expect = "Error on line 10 col 24: ["
		assert(checkRec(input,expect,198))
	}
	test("Function and variable declarations error 2") {
		val input = """
			void f(int a) {
				int f (int g)
				{}
			}
			float k(int a, int b) {
				
			}
      	int main(string [] a, int b){}
		"""
		val expect = "Error on line 3 col 11: ("
		assert(checkRec(input,expect,199))
	}
	
	test("Function and variable declarations error 3") {
		val input = """
			void f(int a) {
				do { break; }
				while(false);
				for (a,,)
				{}
			}
		"""
		val expect = "Error on line 5 col 11: ,"
		assert(checkRec(input,expect,200))
	}

}