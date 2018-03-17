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
  test("more complex program") {
    val input ="""int main () {
	putIntuLn(4542);
  }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,151))
  }
  test("wrong program"){
    val input = "} int main {"
    val expect = "Error on line 1 col 1: }"
    assert(checkRec(input,expect,152))
  }
}