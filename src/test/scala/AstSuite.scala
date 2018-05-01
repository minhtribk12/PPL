import org.scalatest.FunSuite
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class AstSuite extends FunSuite with TestAst {
test("a simple program 1") {
  val input = """
  int a, b;
  int main()
  {
    int a, b;
    swap(a,b);
    a = --3 --2;
    a = (b + c) * d;
    return 0;
  }
  """
  val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(CallExpr(Id("swap"),List(Id("a"),Id("b"))),BinaryOp("=",Id("a"),BinaryOp("-",UnaryOp("-",UnaryOp("-",IntLiteral(3))),UnaryOp("-",IntLiteral(2)))),BinaryOp("=",Id("a"),BinaryOp("*",BinaryOp("+",Id("b"),Id("c")),Id("d"))),Return(Some(IntLiteral(0))))))))
  assert(checkAst(input,expected,201))
}
  test("another simple program 2") {
    val input = "int main () {}"
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,202))
  }
  test("a simple program 3") {
    val input = "void main () {putIntLn(5);}"
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("putIntLn"),List(IntLiteral(5))))))))
    assert(checkAst(input,expected,203))
  }
  test("test dangling if 4") {
    val input = """
    void main () {
      if(a) 
        if(b) 
          a = b;
        else
          b = a;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,204))
  }
  test("test funcdecl 5") {
    val input = """
    int main()
    {
	    int a, b;
	    swap(a,b);
	    return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType), VarDecl(Id("b"),IntType)),List(CallExpr(Id("swap"),List(Id("a"),Id("b"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,205))
  }
  test("test funcdecl 6") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),IntType)),List(BinaryOp("=",Id("c"),CallExpr(Id("tong"),List(Id("a"),Id("b")))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,206))
  }
  test("test funcdecl 7") {
    val input = """
    int max(int a, int b)
    {
      if (a > b)
        return a;
      else
        return b;
    }

    int main()
    {
      int c, d, e;
      e = max(c,d);
      printf("e = %d\n", e);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("max"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),IntType,Block(List(),List(If(BinaryOp(">",Id("a"),Id("b")),Return(Some(Id("a"))),Some(Return(Some(Id("b")))))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("c"),IntType),VarDecl(Id("d"),IntType),VarDecl(Id("e"),IntType)),List(BinaryOp("=",Id("e"),CallExpr(Id("max"),List(Id("c"),Id("d")))),CallExpr(Id("printf"),List(StringLiteral("""e = %d\n"""),Id("e"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,207))
  }
  test("test funcdecl 8") {
    val input = """
    int cubic(int a)
    {
      return a * a * a;
    }

    int main()
    {
      int i;
      for(i = 0; i < 10; i = i + 1)
      {
        a = cubic(a);
      }
      printf("a = %d\n", a); 
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("cubic"),List(VarDecl(Id("a"),IntType)),IntType,Block(List(),List(Return(Some(BinaryOp("*",BinaryOp("*",Id("a"),Id("a")),Id("a"))))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType)),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),IntLiteral(10)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(BinaryOp("=",Id("a"),CallExpr(Id("cubic"),List(Id("a"))))))),CallExpr(Id("printf"),List(StringLiteral("""a = %d\n"""),Id("a"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,208))
  }
  test("test funcdecl 9") {
    val input = """
    int power(int a, int n)
    {
      int i, result;
      result = 1;
      for (i = 0; i < n; i = i + 1)
        result = result * a;
      return result;
    }

    int main()
    {
      int a, n, result;
      a = 5;
      n = 3;
      result = power(a, n);
      printf("%d^%d = %d\n", a, n, result); 
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("power"),List(VarDecl(Id("a"),IntType),VarDecl(Id("n"),IntType)),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("result"),IntType)),List(BinaryOp("=",Id("result"),IntLiteral(1)),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),BinaryOp("=",Id("result"),BinaryOp("*",Id("result"),Id("a")))),Return(Some(Id("result")))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("n"),IntType),VarDecl(Id("result"),IntType)),List(BinaryOp("=",Id("a"),IntLiteral(5)),BinaryOp("=",Id("n"),IntLiteral(3)),BinaryOp("=",Id("result"),CallExpr(Id("power"),List(Id("a"),Id("n")))),CallExpr(Id("printf"),List(StringLiteral("""%d^%d = %d\n"""),Id("a"),Id("n"),Id("result"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,209))
  }
  test("test funcdecl 10") {
    val input = """
    int a;
    boolean equal(int b) {
        if(a == b) return true;
        return false;
    }
    int main() {
    }
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),FuncDecl(Id("equal"),List(VarDecl(Id("b"),IntType)),BoolType,Block(List(),List(If(BinaryOp("==",Id("a"),Id("b")),Return(Some(BooleanLiteral(true))),None),Return(Some(BooleanLiteral(false)))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,210))
  }
  test("test funcdecl 11") {
    val input = """
    int a;
    int plus(int b){
        int rs;
        if (b > 0) rs = a + b;
        else rs = 0;
        return rs;
    }
    int main(){
    }
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),FuncDecl(Id("plus"),List(VarDecl(Id("b"),IntType)),IntType,Block(List(VarDecl(Id("rs"),IntType)),List(If(BinaryOp(">",Id("b"),IntLiteral(0)),BinaryOp("=",Id("rs"),BinaryOp("+",Id("a"),Id("b"))),Some(BinaryOp("=",Id("rs"),IntLiteral(0)))),Return(Some(Id("rs")))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,211))
  }
  test("test funcdecl 12") {
    val input = """
    int a;
    int min(int b, int c, int d) {
        int rs;
        rs = b;
        if (rs > c) rs = c;
        else if (rs > d) rs = d;
        return rs;
    }
    int main(){
    }
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),FuncDecl(Id("min"),List(VarDecl(Id("b"),IntType),VarDecl(Id("c"),IntType),VarDecl(Id("d"),IntType)),IntType,Block(List(VarDecl(Id("rs"),IntType)),List(BinaryOp("=",Id("rs"),Id("b")),If(BinaryOp(">",Id("rs"),Id("c")),BinaryOp("=",Id("rs"),Id("c")),Some(If(BinaryOp(">",Id("rs"),Id("d")),BinaryOp("=",Id("rs"),Id("d")),None))),Return(Some(Id("rs")))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,212))
  }
  test("test funcdecl 13") {
    val input = """
    int a;
    int max(int b, int c, int d) {
        int rs;
        rs = b;
        if (rs < c) 
            if (c > d) rs = c; 
            else rs = d;
        else if (rs < d) 
            if (d > c) rs = d; 
            else rs = c;
        return rs;
    }
    int main(){
    }
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),FuncDecl(Id("max"),List(VarDecl(Id("b"),IntType),VarDecl(Id("c"),IntType),VarDecl(Id("d"),IntType)),IntType,Block(List(VarDecl(Id("rs"),IntType)),List(BinaryOp("=",Id("rs"),Id("b")),If(BinaryOp("<",Id("rs"),Id("c")),If(BinaryOp(">",Id("c"),Id("d")),BinaryOp("=",Id("rs"),Id("c")),Some(BinaryOp("=",Id("rs"),Id("d")))),Some(If(BinaryOp("<",Id("rs"),Id("d")),If(BinaryOp(">",Id("d"),Id("c")),BinaryOp("=",Id("rs"),Id("d")),Some(BinaryOp("=",Id("rs"),Id("c")))),None))),Return(Some(Id("rs")))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,213))
  }
  test("test funcdecl 14") {
    val input = """
    int main(){
      if (!a) {
        if (a <= b) a = 1 + c;
        else a = 0;
      } else {
        if (f()) a = 1;
        else {}
      }
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(UnaryOp("!",Id("a")),Block(List(),List(If(BinaryOp("<=",Id("a"),Id("b")),BinaryOp("=",Id("a"),BinaryOp("+",IntLiteral(1),Id("c"))),Some(BinaryOp("=",Id("a"),IntLiteral(0)))))),Some(Block(List(),List(If(CallExpr(Id("f"),List()),BinaryOp("=",Id("a"),IntLiteral(1)),Some(Block(List(),List()))))))))))))
    assert(checkAst(input,expected,214))
  }
  test("test Array element 15") {
    val input = """
    int[] tong(int a[], int b)
    {
      a[1] = a[2] + 1;
      a[b[2]] = 1;
    }
    int main()
    {
      int a[2], b, c;
      c = tong(a[d], b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("tong"),List(VarDecl(Id("a"),ArrayPointerType(IntType)),VarDecl(Id("b"),IntType)),ArrayPointerType(IntType),Block(List(),List(BinaryOp("=",ArrayCell(Id("a"),IntLiteral(1)),BinaryOp("+",ArrayCell(Id("a"),IntLiteral(2)),IntLiteral(1))),BinaryOp("=",ArrayCell(Id("a"),ArrayCell(Id("b"),IntLiteral(2))),IntLiteral(1))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),ArrayType(IntLiteral(2),IntType)),VarDecl(Id("b"),IntType),VarDecl(Id("c"),IntType)),List(BinaryOp("=",Id("c"),CallExpr(Id("tong"),List(ArrayCell(Id("a"),Id("d")),Id("b")))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,215))
  }
  test("test Array element 16") {
    val input = """
    int main() {
        if (true) 
				{
					a = 1;
        	a[b[d]] = 2;
				}
        else if (false) a = 2;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(BooleanLiteral(true),Block(List(),List(BinaryOp("=",Id("a"),IntLiteral(1)),BinaryOp("=",ArrayCell(Id("a"),ArrayCell(Id("b"),Id("d"))),IntLiteral(2)))),Some(If(BooleanLiteral(false),BinaryOp("=",Id("a"),IntLiteral(2)),None))))))))
		assert(checkAst(input,expected,216))
  }
  test("test if statement 17") {
    val input = """
    int main() {
        if (a>2.1)
            if (b<-1)
                if (c == 2)
                    d = 4;
                    a = 2;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(BinaryOp(">",Id("a"),FloatLiteral(2.1f)),If(BinaryOp("<",Id("b"),UnaryOp("-",IntLiteral(1))),If(BinaryOp("==",Id("c"),IntLiteral(2)),BinaryOp("=",Id("d"),IntLiteral(4)),None),None),None),BinaryOp("=",Id("a"),IntLiteral(2)))))))
    assert(checkAst(input,expected,217))
  }
  test("test priority 18") {
    val input = """
    int main()
    {
      int a, b;
      float a, b;
      a = b = a + b + a * b + a && b / c % -d || !a;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("a"),FloatType),VarDecl(Id("b"),FloatType)),List(BinaryOp("=",Id("a"),BinaryOp("=",Id("b"),BinaryOp("||",BinaryOp("&&",BinaryOp("+",BinaryOp("+",BinaryOp("+",Id("a"),Id("b")),BinaryOp("*",Id("a"),Id("b"))),Id("a")),BinaryOp("%",BinaryOp("/",Id("b"),Id("c")),UnaryOp("-",Id("d")))),UnaryOp("!",Id("a"))))))))))
    assert(checkAst(input,expected,218))
  }
  test("test funcall 19") {
    val input = """
    int sum(int a, int b) {
      int tong;
      float src;
      tong = a + b;
      return tong;
    }

    float sub(float a, float b) {
      float hieu;
      float src;
      hieu = a - b;
      return hieu;
    }

    int main() {
      sum(5 + 1, 1 + 6);
      sub(5 * 1, 1 * 4);
    }
    """
    val expected = Program(List(FuncDecl(Id("sum"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),IntType,Block(List(VarDecl(Id("tong"),IntType),VarDecl(Id("src"),FloatType)),List(BinaryOp("=",Id("tong"),BinaryOp("+",Id("a"),Id("b"))),Return(Some(Id("tong")))))),FuncDecl(Id("sub"),List(VarDecl(Id("a"),FloatType),VarDecl(Id("b"),FloatType)),FloatType,Block(List(VarDecl(Id("hieu"),FloatType),VarDecl(Id("src"),FloatType)),List(BinaryOp("=",Id("hieu"),BinaryOp("-",Id("a"),Id("b"))),Return(Some(Id("hieu")))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("sum"),List(BinaryOp("+",IntLiteral(5),IntLiteral(1)),BinaryOp("+",IntLiteral(1),IntLiteral(6)))),CallExpr(Id("sub"),List(BinaryOp("*",IntLiteral(5),IntLiteral(1)),BinaryOp("*",IntLiteral(1),IntLiteral(4)))))))))
    assert(checkAst(input,expected,219))
  }
  test("test complex program 20") {
    val input = """
    int a, b[2];
    float c, d[2];
    void test(string k[], string a, float b, boolean c)
    {
      {
        a = b;
      }
      k[1] = b = a + b * true = c;
    }
    float[] _test2_(boolean k[])
    {
      /*
      asdfa //
      asdf
      */
      int f[2], y, x[2];
      f = 2.3;
      u = -2;
      n = !-a + b;
      foo(3)[a[1]] = b[c[foo(e)]] + 2;
      {
        //afdadfad /*
        int a_8, _h7, L_, c[4];
        k[a] = 1;
        break;
        return;
        a == (c == d);
        return k[c];
        continue;
        do
        {
          {
            b = -22.3;
            e = 23.32e-2;
            f = 1.;
            y = .1;
            x = 1E-2;
            k = .1E23;
            c = 1;
            d = true;
            i = false;
            a = "abc";
            l = "\n \b \r \b \t \f \" \' \\ ";
          }
        }
        while(a);
      }
      g = 3.2;
    }
    string e, f[2];
    boolean g, h[2];
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayType(IntLiteral(2),IntType)),VarDecl(Id("c"),FloatType),VarDecl(Id("d"),ArrayType(IntLiteral(2),FloatType)),FuncDecl(Id("test"),List(VarDecl(Id("k"),ArrayPointerType(StringType)),VarDecl(Id("a"),StringType),VarDecl(Id("b"),FloatType),VarDecl(Id("c"),BoolType)),VoidType,Block(List(),List(Block(List(),List(BinaryOp("=",Id("a"),Id("b")))),BinaryOp("=",ArrayCell(Id("k"),IntLiteral(1)),BinaryOp("=",Id("b"),BinaryOp("=",BinaryOp("+",Id("a"),BinaryOp("*",Id("b"),BooleanLiteral(true))),Id("c"))))))),FuncDecl(Id("_test2_"),List(VarDecl(Id("k"),ArrayPointerType(BoolType))),ArrayPointerType(FloatType),Block(List(VarDecl(Id("f"),ArrayType(IntLiteral(2),IntType)),VarDecl(Id("y"),IntType),VarDecl(Id("x"),ArrayType(IntLiteral(2),IntType))),List(BinaryOp("=",Id("f"),FloatLiteral(2.3f)),BinaryOp("=",Id("u"),UnaryOp("-",IntLiteral(2))),BinaryOp("=",Id("n"),BinaryOp("+",UnaryOp("!",UnaryOp("-",Id("a"))),Id("b"))),BinaryOp("=",ArrayCell(CallExpr(Id("foo"),List(IntLiteral(3))),ArrayCell(Id("a"),IntLiteral(1))),BinaryOp("+",ArrayCell(Id("b"),ArrayCell(Id("c"),CallExpr(Id("foo"),List(Id("e"))))),IntLiteral(2))),Block(List(VarDecl(Id("a_8"),IntType),VarDecl(Id("_h7"),IntType),VarDecl(Id("L_"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(4),IntType))),List(BinaryOp("=",ArrayCell(Id("k"),Id("a")),IntLiteral(1)),Break,Return(None),BinaryOp("==",Id("a"),BinaryOp("==",Id("c"),Id("d"))),Return(Some(ArrayCell(Id("k"),Id("c")))),Continue,Dowhile(List(Block(List(),List(Block(List(),List(BinaryOp("=",Id("b"),UnaryOp("-",FloatLiteral(22.3f))),BinaryOp("=",Id("e"),FloatLiteral(0.2332f)),BinaryOp("=",Id("f"),FloatLiteral(1.0f)),BinaryOp("=",Id("y"),FloatLiteral(0.1f)),BinaryOp("=",Id("x"),FloatLiteral(0.01f)),BinaryOp("=",Id("k"),FloatLiteral(1.0E22f)),BinaryOp("=",Id("c"),IntLiteral(1)),BinaryOp("=",Id("d"),BooleanLiteral(true)),BinaryOp("=",Id("i"),BooleanLiteral(false)),BinaryOp("=",Id("a"),StringLiteral("""abc""")),BinaryOp("=",Id("l"),StringLiteral("""\n \b \r \b \t \f \" \' \\ """))))))),Id("a")))),BinaryOp("=",Id("g"),FloatLiteral(3.2f))))),VarDecl(Id("e"),StringType),VarDecl(Id("f"),ArrayType(IntLiteral(2),StringType)),VarDecl(Id("g"),BoolType),VarDecl(Id("h"),ArrayType(IntLiteral(2),BoolType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),IntType)),List(BinaryOp("=",Id("c"),CallExpr(Id("tong"),List(Id("a"),Id("b")))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,220))
  }
  test("test funcdeclare 21") {
    val input = """
    int ti(){
	  2 + 4;
	  10 < 20;
	}
    """
    val expected = Program(List(FuncDecl(Id("ti"),List(),IntType,Block(List(),List(BinaryOp("+",IntLiteral(2),IntLiteral(4)),BinaryOp("<",IntLiteral(10),IntLiteral(20)))))))
    assert(checkAst(input,expected,221))
  }
  test("test var declare 22") {
    val input = """
    void getID(int a, int b){
	  int c;
	  int d;
	  2 + 4;
	  10 < 20;
	}
    """
    val expected = Program(List(FuncDecl(Id("getID"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),VoidType,Block(List(VarDecl(Id("c"),IntType),VarDecl(Id("d"),IntType)),List(BinaryOp("+",IntLiteral(2),IntLiteral(4)),BinaryOp("<",IntLiteral(10),IntLiteral(20)))))))
    assert(checkAst(input,expected,222))
  }
  test("test funcdeclare 23") {
    val input = """
	int func1(int para1){
		float r;
		para1 = r+3;
		return para1;
	}
	boolean func2(int para1, float para2){
		boolean a;
		if(para1>para2){
			a = true;
		}
		else{
			a = false;
		}
		return a;
	}
	void main(){
		int pf_1;
		int i;
		boolean _flag;
		pf_1 = func1(6);
		for(i=0;i>5;i=i+1){
			if(_flag==false){
				pf_1 = i + 5;
			}
			else {
				pf_1 = i + 6;
				}
		}
	}

    """
    val expected =Program(List(FuncDecl(Id("func1"),List(VarDecl(Id("para1"),IntType)),IntType,Block(List(VarDecl(Id("r"),FloatType)),List(BinaryOp("=",Id("para1"),BinaryOp("+",Id("r"),IntLiteral(3))),Return(Some(Id("para1")))))),FuncDecl(Id("func2"),List(VarDecl(Id("para1"),IntType),VarDecl(Id("para2"),FloatType)),BoolType,Block(List(VarDecl(Id("a"),BoolType)),List(If(BinaryOp(">",Id("para1"),Id("para2")),Block(List(),List(BinaryOp("=",Id("a"),BooleanLiteral(true)))),Some(Block(List(),List(BinaryOp("=",Id("a"),BooleanLiteral(false)))))),Return(Some(Id("a")))))),FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("pf_1"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("_flag"),BoolType)),List(BinaryOp("=",Id("pf_1"),CallExpr(Id("func1"),List(IntLiteral(6)))),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp(">",Id("i"),IntLiteral(5)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",Id("_flag"),BooleanLiteral(false)),Block(List(),List(BinaryOp("=",Id("pf_1"),BinaryOp("+",Id("i"),IntLiteral(5))))),Some(Block(List(),List(BinaryOp("=",Id("pf_1"),BinaryOp("+",Id("i"),IntLiteral(6)))))))))))))))
    assert(checkAst(input,expected,223))
  }
  test("test for statement 24") {
    val input = """
	int func1()
	{
		for(i = 0; i < n; i = i + 1)
			sum = sum + a[i];
	}
	int main() {
	}

    """
    val expected = Program(List(FuncDecl(Id("func1"),List(),IntType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),BinaryOp("=",Id("sum"),BinaryOp("+",Id("sum"),ArrayCell(Id("a"),Id("i")))))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,224))
  }
  test("test for statement 25") {
    val input = """
	int func1()
	{
		for(i = 0; i < n; i = i + 1)
			for(j = 0; j < i; j = j + 1)
				sum = sum + a[j];
	}
	int main() {
	}

    """
    val expected = Program(List(FuncDecl(Id("func1"),List(),IntType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),Id("i")),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),BinaryOp("=",Id("sum"),BinaryOp("+",Id("sum"),ArrayCell(Id("a"),Id("j"))))))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,225))
  }
  test("test for statement 26") {
    val input = """
	int func1()
	{
		for(i = 0; i < n; i = i + 1)
			for(j = 0; j < i; j = j + 1)
				if(j < m)
					sum = sum + a[j];
				else
					sum = sum - a[j];
	}
	int main() {
	}

    """
    val expected = Program(List(FuncDecl(Id("func1"),List(),IntType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),Id("i")),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),If(BinaryOp("<",Id("j"),Id("m")),BinaryOp("=",Id("sum"),BinaryOp("+",Id("sum"),ArrayCell(Id("a"),Id("j")))),Some(BinaryOp("=",Id("sum"),BinaryOp("-",Id("sum"),ArrayCell(Id("a"),Id("j"))))))))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,226))
  }
  test("test for statement 27") {
    val input = """
	int func1()
	{
		for(i = 0; i < n; i = i + 1)
			for(j = 0; j < i; j = j + 1)
			{
				if(j < m)
				{
					break;
				}
				else
				{
					a[j] = a[j] * 2;
				}
				sum = sum - (a[i] + a[j]);
			}
	}
	int main() {
	}

    """
    val expected = Program(List(FuncDecl(Id("func1"),List(),IntType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),Id("i")),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),Block(List(),List(If(BinaryOp("<",Id("j"),Id("m")),Block(List(),List(Break)),Some(Block(List(),List(BinaryOp("=",ArrayCell(Id("a"),Id("j")),BinaryOp("*",ArrayCell(Id("a"),Id("j")),IntLiteral(2))))))),BinaryOp("=",Id("sum"),BinaryOp("-",Id("sum"),BinaryOp("+",ArrayCell(Id("a"),Id("i")),ArrayCell(Id("a"),Id("j")))))))))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,227))
  }
  test("test func + var declare 28") {
    val input = """
	int a;
	float b;
	int f1() {return 1;}
	int f2() {return 2;}

    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),FloatType),FuncDecl(Id("f1"),List(),IntType,Block(List(),List(Return(Some(IntLiteral(1)))))),FuncDecl(Id("f2"),List(),IntType,Block(List(),List(Return(Some(IntLiteral(2))))))))
    assert(checkAst(input,expected,228))
  }
  test("test func + var decalare 29") {
    val input = """
	int a;
	float b;
	int f1() {int c; int d; return 1;}

    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),FloatType),FuncDecl(Id("f1"),List(),IntType,Block(List(VarDecl(Id("c"),IntType),VarDecl(Id("d"),IntType)),List(Return(Some(IntLiteral(1))))))))
    assert(checkAst(input,expected,229))
  }
  test("test for 30") {
    val input = """
    int func1()
    {
      for(i = 0; i < n; i = i + 1)
        for(j = 0; j < i; j = j + 1)
          sum = sum + a[j];
    }
    """
    val expected = Program(List(FuncDecl(Id("func1"),List(),IntType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),Id("i")),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),BinaryOp("=",Id("sum"),BinaryOp("+",Id("sum"),ArrayCell(Id("a"),Id("j")))))))))))
    assert(checkAst(input,expected,230))
  }
  test("test boolean function 31") {
    val input = """
	int a;
	float b;
	boolean f1() {int c; int d; c=1; d=2; return c==d;}

    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),FloatType),FuncDecl(Id("f1"),List(),BoolType,Block(List(VarDecl(Id("c"),IntType),VarDecl(Id("d"),IntType)),List(BinaryOp("=",Id("c"),IntLiteral(1)),BinaryOp("=",Id("d"),IntLiteral(2)),Return(Some(BinaryOp("==",Id("c"),Id("d")))))))))
    assert(checkAst(input,expected,231))
  }
  test("test void function 32") {
    val input = """
	void test()  { int a; return; }
	void test2() { int b; return; }
	int main() {}
    """
    val expected = Program(List(FuncDecl(Id("test"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType)),List(Return(None)))),FuncDecl(Id("test2"),List(),VoidType,Block(List(VarDecl(Id("b"),IntType)),List(Return(None)))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,232))
  }
  test("test void function 33") {
    val input = """
	void test()  { int a; a = a + 1; return; }
	void test2() { int b; test(); return; }
	int main() {}
    """
    val expected = Program(List(FuncDecl(Id("test"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1))),Return(None)))),FuncDecl(Id("test2"),List(),VoidType,Block(List(VarDecl(Id("b"),IntType)),List(CallExpr(Id("test"),List()),Return(None)))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,233))
  }
  test("test void function 34") {
    val input = """
	void test()  { int a; a = a + 1; return; }
	void test2() { int b; test(); return; }
	void test3() {
		for(i=1;i<3;i=i+1) {
			if(i==2) {
				break;
			} else {
				continue;
			}
		}
		return;
	}
	int main() {}
    """
    val expected = Program(List(FuncDecl(Id("test"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1))),Return(None)))),FuncDecl(Id("test2"),List(),VoidType,Block(List(VarDecl(Id("b"),IntType)),List(CallExpr(Id("test"),List()),Return(None)))),FuncDecl(Id("test3"),List(),VoidType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<",Id("i"),IntLiteral(3)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",Id("i"),IntLiteral(2)),Block(List(),List(Break)),Some(Block(List(),List(Continue))))))),Return(None)))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,234))
  }
  test("test do while 35") {
    val input = """
	void test()  { int a; a = a + 1; return; }
	void test2() { int b; test(); return; }
	void test3() {

		do
			test2();
		while (1==1);

		for(i=1;i<3;i=i+1) {
			if(i==2) {
				break;
			} else {
				continue;
			}
		}
		return;
	}
	int main() {}
    """
    val expected = Program(List(FuncDecl(Id("test"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1))),Return(None)))),FuncDecl(Id("test2"),List(),VoidType,Block(List(VarDecl(Id("b"),IntType)),List(CallExpr(Id("test"),List()),Return(None)))),FuncDecl(Id("test3"),List(),VoidType,Block(List(),List(Dowhile(List(CallExpr(Id("test2"),List())),BinaryOp("==",IntLiteral(1),IntLiteral(1))),For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<",Id("i"),IntLiteral(3)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",Id("i"),IntLiteral(2)),Block(List(),List(Break)),Some(Block(List(),List(Continue))))))),Return(None)))),FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,235))
  }
  test("test boolean function 36") {
    val input = """
    int a;
    float b;
    boolean f1() {int c; int d; c=1; d=2; return c==d;}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),FloatType),FuncDecl(Id("f1"),List(),BoolType,Block(List(VarDecl(Id("c"),IntType),VarDecl(Id("d"),IntType)),List(BinaryOp("=",Id("c"),IntLiteral(1)),BinaryOp("=",Id("d"),IntLiteral(2)),Return(Some(BinaryOp("==",Id("c"),Id("d")))))))))
    assert(checkAst(input,expected,236))
  }
  test("test literal 37") {
    val input = """
    int main()
    {
      int a, b[2];
      float a, b[2];
      boolean a, b[2];
      string a, b[2];
    }
    void a (int a[], float b[], string c[], int d, float e, string f){
      a["ewe"] = "abc" + 2.1 + true;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayType(IntLiteral(2),IntType)),VarDecl(Id("a"),FloatType),VarDecl(Id("b"),ArrayType(IntLiteral(2),FloatType)),VarDecl(Id("a"),BoolType),VarDecl(Id("b"),ArrayType(IntLiteral(2),BoolType)),VarDecl(Id("a"),StringType),VarDecl(Id("b"),ArrayType(IntLiteral(2),StringType))),List())),FuncDecl(Id("a"),List(VarDecl(Id("a"),ArrayPointerType(IntType)),VarDecl(Id("b"),ArrayPointerType(FloatType)),VarDecl(Id("c"),ArrayPointerType(StringType)),VarDecl(Id("d"),IntType),VarDecl(Id("e"),FloatType),VarDecl(Id("f"),StringType)),VoidType,Block(List(),List(BinaryOp("=",ArrayCell(Id("a"),StringLiteral("""ewe""")),BinaryOp("+",BinaryOp("+",StringLiteral("""abc"""),FloatLiteral(2.1f)),BooleanLiteral(true))))))))
    assert(checkAst(input,expected,237))
  }
  test("test do while 38") {
    val input = """
    int main()
    {
      do 
      {
        if(a = b)
        {
          break;
        }
        else
        {
          continue;
        }
        a = b;
        c = d;
      }
      {
        a = c;
      }
      while(a < b);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Dowhile(List(Block(List(),List(If(BinaryOp("=",Id("a"),Id("b")),Block(List(),List(Break)),Some(Block(List(),List(Continue)))),BinaryOp("=",Id("a"),Id("b")),BinaryOp("=",Id("c"),Id("d")))),Block(List(),List(BinaryOp("=",Id("a"),Id("c"))))),BinaryOp("<",Id("a"),Id("b"))))))))
    assert(checkAst(input,expected,238))
  }
  test("test multi decalare 39") {
    val input = """
	int a, b, c[50];
	float d, e, f[50];
	void func1(int a, float b[]){}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(50),IntType)),VarDecl(Id("d"),FloatType),VarDecl(Id("e"),FloatType),VarDecl(Id("f"),ArrayType(IntLiteral(50),FloatType)),FuncDecl(Id("func1"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayPointerType(FloatType))),VoidType,Block(List(),List()))))
    assert(checkAst(input,expected,239))
  }
  test("test multi decalre 40") {
    val input = """
	int a, b, c[5];
	void func(int a, boolean b[]){}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("func"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayPointerType(BoolType))),VoidType,Block(List(),List()))))
    assert(checkAst(input,expected,240))
  }
  test("test multi decalare 41") {
    val input = """
	int a, b, c[5];
	int main(){
		float d;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType)),List()))))
    assert(checkAst(input,expected,241))
  }
  test("test var decalre 42") {
    val input = """
	int a, b, c[5];
	int main(){
		float d;
		int e;
		boolean f[5];
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),ArrayType(IntLiteral(5),BoolType))),List()))))
    assert(checkAst(input,expected,242))
  }
  test("test var declare 43") {
    val input = """
	int a, b, c[5];
	int main(){
		float d;
		int a;
	}

    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("a"),IntType)),List()))))
    assert(checkAst(input,expected,243))
  }
  test("test var declare 44") {
    val input = """
	int a, b, c[5];
	int main(){
		float d[5];
	}

    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),ArrayType(IntLiteral(5),FloatType))),List()))))
    assert(checkAst(input,expected,244))
  }
  test("test var decalare 45") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		e = f = 2;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(BinaryOp("=",Id("e"),BinaryOp("=",Id("f"),IntLiteral(2))))))))
    assert(checkAst(input,expected,245))
  }
  test("test if statement 46") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		if (e == 2)
			g[1] = 1;

		if (f == 1)
			g[1] = 1;
		else g[1] = 0;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(If(BinaryOp("==",Id("e"),IntLiteral(2)),BinaryOp("=",ArrayCell(Id("g"),IntLiteral(1)),IntLiteral(1)),None),If(BinaryOp("==",Id("f"),IntLiteral(1)),BinaryOp("=",ArrayCell(Id("g"),IntLiteral(1)),IntLiteral(1)),Some(BinaryOp("=",ArrayCell(Id("g"),IntLiteral(1)),IntLiteral(0)))))))))
    assert(checkAst(input,expected,246))
  }
  test("test do while 47") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		do
			e = e + 1;
			putInt(e);
		while(e > 5);
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(Dowhile(List(BinaryOp("=",Id("e"),BinaryOp("+",Id("e"),IntLiteral(1))),CallExpr(Id("putInt"),List(Id("e")))),BinaryOp(">",Id("e"),IntLiteral(5))))))))
    assert(checkAst(input,expected,247))
  }
  test("test for statement 48") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		for(e = 1; e < 100; e = e + 1)
			f = f + 2;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(For(BinaryOp("=",Id("e"),IntLiteral(1)),BinaryOp("<",Id("e"),IntLiteral(100)),BinaryOp("=",Id("e"),BinaryOp("+",Id("e"),IntLiteral(1))),BinaryOp("=",Id("f"),BinaryOp("+",Id("f"),IntLiteral(2)))))))))
    assert(checkAst(input,expected,248))
  }
  test("test break statemnet 49") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		break;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(Break)))))
    assert(checkAst(input,expected,249))
  }
  test("test continue statement 50") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		continue;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(Continue)))))
    assert(checkAst(input,expected,250))
  }
  test("test return 51") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		return;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(Return(None))))))
    assert(checkAst(input,expected,251))
  }
  test("test return statement 52") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		return e;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(Return(Some(Id("e"))))))))
    assert(checkAst(input,expected,252))
  }
  test("test return statement 53") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		return putInt(a);
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(Return(Some(CallExpr(Id("putInt"),List(Id("a"))))))))))
    assert(checkAst(input,expected,253))
  }
  test("test funcall 54") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		foo(1,2);
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(CallExpr(Id("foo"),List(IntLiteral(1),IntLiteral(2))))))))
    assert(checkAst(input,expected,254))
  }
  test("test funcall 55") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		f = 1;
		foo(1,2);
		e + 1;
		100;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(BinaryOp("=",Id("f"),IntLiteral(1)),CallExpr(Id("foo"),List(IntLiteral(1),IntLiteral(2))),BinaryOp("+",Id("e"),IntLiteral(1)),IntLiteral(100))))))
    assert(checkAst(input,expected,255))
  }
  test("test block statement 56") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		{
		f = 1;
		foo(1,2);
		e + 1;
		100;
		}
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(Block(List(),List(BinaryOp("=",Id("f"),IntLiteral(1)),CallExpr(Id("foo"),List(IntLiteral(1),IntLiteral(2))),BinaryOp("+",Id("e"),IntLiteral(1)),IntLiteral(100))))))))
    assert(checkAst(input,expected,256))
  }
  test("test block statement 57") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		//float d;
		//int e, f, g[7];

		// statement part
		{
		f = 1;
		foo(1,2);
		e + 1;
		100;
		}
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(),List(Block(List(),List(BinaryOp("=",Id("f"),IntLiteral(1)),CallExpr(Id("foo"),List(IntLiteral(1),IntLiteral(2))),BinaryOp("+",Id("e"),IntLiteral(1)),IntLiteral(100))))))))
    assert(checkAst(input,expected,257))
  }
  test("test block statement 58") {
    val input = """
	int a, b, c[5];

	int main(){
			int a, b, c[50];
		{
			f = 1;
			foo(1,2);
			e + 1;
			100;
		}
	}

    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(50),IntType))),List(Block(List(),List(BinaryOp("=",Id("f"),IntLiteral(1)),CallExpr(Id("foo"),List(IntLiteral(1),IntLiteral(2))),BinaryOp("+",Id("e"),IntLiteral(1)),IntLiteral(100))))))))
    assert(checkAst(input,expected,258))
  }
  test("test block statement 59") {
    val input = """
	/* var declaration */
	int i;

	// function declaration
	int f(){
		return 200;
	}

	int main(){
		// declaration part
		int main;

		// statement part
		main = f();
		putIntLn(i);
		{	// block statement
			int i;
			int main;
			int f;

			main = f = i = 100;
			putIntLn(i);
			putIntLn(main);
			putIntLn(f);
		}

		putIntLn(main);

	}
    """
    val expected = Program(List(VarDecl(Id("i"),IntType),FuncDecl(Id("f"),List(),IntType,Block(List(),List(Return(Some(IntLiteral(200)))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("main"),IntType)),List(BinaryOp("=",Id("main"),CallExpr(Id("f"),List())),CallExpr(Id("putIntLn"),List(Id("i"))),Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("main"),IntType),VarDecl(Id("f"),IntType)),List(BinaryOp("=",Id("main"),BinaryOp("=",Id("f"),BinaryOp("=",Id("i"),IntLiteral(100)))),CallExpr(Id("putIntLn"),List(Id("i"))),CallExpr(Id("putIntLn"),List(Id("main"))),CallExpr(Id("putIntLn"),List(Id("f"))))),CallExpr(Id("putIntLn"),List(Id("main"))))))))
    assert(checkAst(input,expected,259))
  }
  test("test block statement 60") {
    val input = """
	/* var declaration */
	int i;

	// function declaration
	int f(){
		return 200;
	}

	int main(){
		// declaration part
		int main;

		// statement part
		main = f();
		putIntLn(i);
		{	// block statement
			int i;
			int main;
			int f;

			main = f = i = 100;
			putIntLn(i);
			putIntLn(main);
			putIntLn(f);
		}

		if (f == 1)
			g[1] = 1;
		else
			g[1] = 0;

		putIntLn(main);

	}
    """
    val expected = Program(List(VarDecl(Id("i"),IntType),FuncDecl(Id("f"),List(),IntType,Block(List(),List(Return(Some(IntLiteral(200)))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("main"),IntType)),List(BinaryOp("=",Id("main"),CallExpr(Id("f"),List())),CallExpr(Id("putIntLn"),List(Id("i"))),Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("main"),IntType),VarDecl(Id("f"),IntType)),List(BinaryOp("=",Id("main"),BinaryOp("=",Id("f"),BinaryOp("=",Id("i"),IntLiteral(100)))),CallExpr(Id("putIntLn"),List(Id("i"))),CallExpr(Id("putIntLn"),List(Id("main"))),CallExpr(Id("putIntLn"),List(Id("f"))))),If(BinaryOp("==",Id("f"),IntLiteral(1)),BinaryOp("=",ArrayCell(Id("g"),IntLiteral(1)),IntLiteral(1)),Some(BinaryOp("=",ArrayCell(Id("g"),IntLiteral(1)),IntLiteral(0)))),CallExpr(Id("putIntLn"),List(Id("main"))))))))
    assert(checkAst(input,expected,260))
  }
  test("test complex program 61") {
    val input = """
	int a, b, c[5];

	int main(){
		/* declaration */
		float d;
		int e, f, g[7];

		// statement part
		if (e == 2)
			if (f == 1)
				g[1] = 1;
			else
				g[1] = 0;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(5),IntType)),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("d"),FloatType),VarDecl(Id("e"),IntType),VarDecl(Id("f"),IntType),VarDecl(Id("g"),ArrayType(IntLiteral(7),IntType))),List(If(BinaryOp("==",Id("e"),IntLiteral(2)),If(BinaryOp("==",Id("f"),IntLiteral(1)),BinaryOp("=",ArrayCell(Id("g"),IntLiteral(1)),IntLiteral(1)),Some(BinaryOp("=",ArrayCell(Id("g"),IntLiteral(1)),IntLiteral(0)))),None))))))
    assert(checkAst(input,expected,261))
  }
  test("test complex program 62") {
    val input = """
	/* var declaration */
	int i;

	// function declaration
	int f(int a, int b){
		a = 1;
		b = 2;
		return 200;
	}

	int main(){
		// declaration part
		int m;

		// statement part
		main = f();
		putIntLn(i);
		{	// block statement
			int i;
			int main;
			int f;

			main = f = i = 100;
			putIntLn(i);
			putIntLn(main);
			putIntLn(f);

			if (i > 1)
				main = 2;

			for (i = 0; i < 100; i = i * 2){
				printf("number = %d\n", i);
			}

			do
				i = i + 1;
				decrease(i);
			while (i < 0);
		}

		if (f == 1)
			g[1] = 1;
		else
			g[1] = 0;

		putIntLn(main);

	}
    """
    val expected = Program(List(VarDecl(Id("i"),IntType),FuncDecl(Id("f"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),IntType,Block(List(),List(BinaryOp("=",Id("a"),IntLiteral(1)),BinaryOp("=",Id("b"),IntLiteral(2)),Return(Some(IntLiteral(200)))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("m"),IntType)),List(BinaryOp("=",Id("main"),CallExpr(Id("f"),List())),CallExpr(Id("putIntLn"),List(Id("i"))),Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("main"),IntType),VarDecl(Id("f"),IntType)),List(BinaryOp("=",Id("main"),BinaryOp("=",Id("f"),BinaryOp("=",Id("i"),IntLiteral(100)))),CallExpr(Id("putIntLn"),List(Id("i"))),CallExpr(Id("putIntLn"),List(Id("main"))),CallExpr(Id("putIntLn"),List(Id("f"))),If(BinaryOp(">",Id("i"),IntLiteral(1)),BinaryOp("=",Id("main"),IntLiteral(2)),None),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),IntLiteral(100)),BinaryOp("=",Id("i"),BinaryOp("*",Id("i"),IntLiteral(2))),Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""number = %d\n"""),Id("i")))))),Dowhile(List(BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),CallExpr(Id("decrease"),List(Id("i")))),BinaryOp("<",Id("i"),IntLiteral(0))))),If(BinaryOp("==",Id("f"),IntLiteral(1)),BinaryOp("=",ArrayCell(Id("g"),IntLiteral(1)),IntLiteral(1)),Some(BinaryOp("=",ArrayCell(Id("g"),IntLiteral(1)),IntLiteral(0)))),CallExpr(Id("putIntLn"),List(Id("main"))))))))
    assert(checkAst(input,expected,262))
  }
  test("test Stringlit 63") {
    val input = """
	int main()
	{
	   // printf() displays the string inside quotation
	   printf("Hello, World!");
	   return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Hello, World!"""))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,263))
  }
  test("test Stringlit 64") {
    val input = """
	int main()
	{
		int number;

		// printf() dislpays the formatted output 
		printf("Enter an integer: ");  
		
		// scanf() reads the formatted input and stores them
		scanf("%d", number);  
		
		// printf() displays the formatted output
		printf("You entered: %d", number);
		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("number"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter an integer: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("number"))),CallExpr(Id("printf"),List(StringLiteral("""You entered: %d"""),Id("number"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,264))
  }
  test("test Stringlit 65") {
    val input = """
	int main()
	{
		int firstNumber, secondNumber, sumOfTwoNumbers;
		
		printf("Enter two integers: ");

		// Two integers entered by user is stored using scanf() function
		scanf("%d %d", firstNumber, secondNumber);

		// sum of two numbers in stored in variable sumOfTwoNumbers
		sumOfTwoNumbers = firstNumber + secondNumber;

		// Displays sum      
		printf("%d + %d = %d", firstNumber, secondNumber, sumOfTwoNumbers);

		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("firstNumber"),IntType),VarDecl(Id("secondNumber"),IntType),VarDecl(Id("sumOfTwoNumbers"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter two integers: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d %d"""),Id("firstNumber"),Id("secondNumber"))),BinaryOp("=",Id("sumOfTwoNumbers"),BinaryOp("+",Id("firstNumber"),Id("secondNumber"))),CallExpr(Id("printf"),List(StringLiteral("""%d + %d = %d"""),Id("firstNumber"),Id("secondNumber"),Id("sumOfTwoNumbers"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,265))
  }
  test("test Stringlit 66") {
    val input = """
	int firstNumber, secondNumber, sumOfTwoNumbers;

	int main()
	{
		int integerType;
		float floatType;

		// Sizeof operator is used to evaluate the size of a variable
		printf("Size of int: %ld bytes\n",sizeof(integerType));
		printf("Size of float: %ld bytes\n",sizeof(floatType));

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("firstNumber"),IntType),VarDecl(Id("secondNumber"),IntType),VarDecl(Id("sumOfTwoNumbers"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("integerType"),IntType),VarDecl(Id("floatType"),FloatType)),List(CallExpr(Id("printf"),List(StringLiteral("""Size of int: %ld bytes\n"""),CallExpr(Id("sizeof"),List(Id("integerType"))))),CallExpr(Id("printf"),List(StringLiteral("""Size of float: %ld bytes\n"""),CallExpr(Id("sizeof"),List(Id("floatType"))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,266))
  }
  test("test complex program 67") {
    val input = """
	int a, b, c[50];
	float d;

	int main()
	{
		int number;

		printf("Enter an integer: ");
		scanf("%d", number);

		// True if the number is perfectly divisible by 2
		if(number / 2 == 0)
			printf("%d is even.", number);
		else
			printf("%d is odd.", number);

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(50),IntType)),VarDecl(Id("d"),FloatType),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("number"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter an integer: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("number"))),If(BinaryOp("==",BinaryOp("/",Id("number"),IntLiteral(2)),IntLiteral(0)),CallExpr(Id("printf"),List(StringLiteral("""%d is even."""),Id("number"))),Some(CallExpr(Id("printf"),List(StringLiteral("""%d is odd."""),Id("number"))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,267))
  }
  test("test priority 68") {
    val input = """
	boolean a, b, c[50];
	float d;

	int main()
	{
		int isLowercaseVowel, isUppercaseVowel;

		printf("Enter an alphabet: ");
		scanf("%c",c);

		// evaluates to 1 (true) if c is a lowercase vowel
		isLowercaseVowel = (c == 1 || c == 2 || c == 3 || c == 4 || c == 5);

		// evaluates to 1 (true) if c is an uppercase vowel
		isUppercaseVowel = (c == 6 || c == 7 || c == 8 || c == 9 || c == 10);

		// evaluates to 1 (true) if either isLowercaseVowel or isUppercaseVowel is true
		if (isLowercaseVowel || isUppercaseVowel)
			printf("%c is a vowel.", c);
		else
			printf("%c is a consonant.", c);
		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("a"),BoolType),VarDecl(Id("b"),BoolType),VarDecl(Id("c"),ArrayType(IntLiteral(50),BoolType)),VarDecl(Id("d"),FloatType),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("isLowercaseVowel"),IntType),VarDecl(Id("isUppercaseVowel"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter an alphabet: """))),CallExpr(Id("scanf"),List(StringLiteral("""%c"""),Id("c"))),BinaryOp("=",Id("isLowercaseVowel"),BinaryOp("||",BinaryOp("||",BinaryOp("||",BinaryOp("||",BinaryOp("==",Id("c"),IntLiteral(1)),BinaryOp("==",Id("c"),IntLiteral(2))),BinaryOp("==",Id("c"),IntLiteral(3))),BinaryOp("==",Id("c"),IntLiteral(4))),BinaryOp("==",Id("c"),IntLiteral(5)))),BinaryOp("=",Id("isUppercaseVowel"),BinaryOp("||",BinaryOp("||",BinaryOp("||",BinaryOp("||",BinaryOp("==",Id("c"),IntLiteral(6)),BinaryOp("==",Id("c"),IntLiteral(7))),BinaryOp("==",Id("c"),IntLiteral(8))),BinaryOp("==",Id("c"),IntLiteral(9))),BinaryOp("==",Id("c"),IntLiteral(10)))),If(BinaryOp("||",Id("isLowercaseVowel"),Id("isUppercaseVowel")),CallExpr(Id("printf"),List(StringLiteral("""%c is a vowel."""),Id("c"))),Some(CallExpr(Id("printf"),List(StringLiteral("""%c is a consonant."""),Id("c"))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,268))
  }
  test("test associate 69") {
    val input = """
	float n1, n2, n3;

	int main()
	{
		n1 = n2 = n3 = 0;

		printf("Enter three numbers: ");
		scanf("%lf %lf %lf", n1, n2, n3);

		if( n1>=n2 && n1>=n3 )
			printf("%.2f is the largest number.", n1);

		if( n2>=n1 && n2>=n3 )
			printf("%.2f is the largest number.", n2);

		if( n3>=n1 && n3>=n2 )
			printf("%.2f is the largest number.", n3);

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("n1"),FloatType),VarDecl(Id("n2"),FloatType),VarDecl(Id("n3"),FloatType),FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("=",Id("n1"),BinaryOp("=",Id("n2"),BinaryOp("=",Id("n3"),IntLiteral(0)))),CallExpr(Id("printf"),List(StringLiteral("""Enter three numbers: """))),CallExpr(Id("scanf"),List(StringLiteral("""%lf %lf %lf"""),Id("n1"),Id("n2"),Id("n3"))),If(BinaryOp("&&",BinaryOp(">=",Id("n1"),Id("n2")),BinaryOp(">=",Id("n1"),Id("n3"))),CallExpr(Id("printf"),List(StringLiteral("""%.2f is the largest number."""),Id("n1"))),None),If(BinaryOp("&&",BinaryOp(">=",Id("n2"),Id("n1")),BinaryOp(">=",Id("n2"),Id("n3"))),CallExpr(Id("printf"),List(StringLiteral("""%.2f is the largest number."""),Id("n2"))),None),If(BinaryOp("&&",BinaryOp(">=",Id("n3"),Id("n1")),BinaryOp(">=",Id("n3"),Id("n2"))),CallExpr(Id("printf"),List(StringLiteral("""%.2f is the largest number."""),Id("n3"))),None),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,269))
  }
  test("test complex statement 70") {
    val input = """
	float n1, n2, n3;

	int main()
	{
		float a, b, c, determinant, root1,root2, realPart, imaginaryPart;

		printf("Enter coefficients a, b and c: ");
		scanf("%lf %lf %lf", a, b, c);

		determinant = b*b-4*a*c;

		// condition for real and different roots
		if (determinant > 0)
		{
		// sqrt() function returns square root
			root1 = (-b+sqrt(determinant))/(2*a);
			root2 = (-b-sqrt(determinant))/(2*a);

			printf("root1 = %.2lf and root2 = %.2lf",root1 , root2);
		}

		//condition for real and equal roots
		else if (determinant == 0)
		{
			root1 = root2 = -b/(2*a);

			printf("root1 = root2 = %.2lf;", root1);
		}

		// if roots are not real 
		else
		{
			realPart = -b/(2*a);
			imaginaryPart = sqrt(-determinant)/(2*a);
			printf("root1 = %.2lf+%.2lfi and root2 = %.2f-%.2fi", realPart, imaginaryPart, realPart, imaginaryPart);
		}

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("n1"),FloatType),VarDecl(Id("n2"),FloatType),VarDecl(Id("n3"),FloatType),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),FloatType),VarDecl(Id("b"),FloatType),VarDecl(Id("c"),FloatType),VarDecl(Id("determinant"),FloatType),VarDecl(Id("root1"),FloatType),VarDecl(Id("root2"),FloatType),VarDecl(Id("realPart"),FloatType),VarDecl(Id("imaginaryPart"),FloatType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter coefficients a, b and c: """))),CallExpr(Id("scanf"),List(StringLiteral("""%lf %lf %lf"""),Id("a"),Id("b"),Id("c"))),BinaryOp("=",Id("determinant"),BinaryOp("-",BinaryOp("*",Id("b"),Id("b")),BinaryOp("*",BinaryOp("*",IntLiteral(4),Id("a")),Id("c")))),If(BinaryOp(">",Id("determinant"),IntLiteral(0)),Block(List(),List(BinaryOp("=",Id("root1"),BinaryOp("/",BinaryOp("+",UnaryOp("-",Id("b")),CallExpr(Id("sqrt"),List(Id("determinant")))),BinaryOp("*",IntLiteral(2),Id("a")))),BinaryOp("=",Id("root2"),BinaryOp("/",BinaryOp("-",UnaryOp("-",Id("b")),CallExpr(Id("sqrt"),List(Id("determinant")))),BinaryOp("*",IntLiteral(2),Id("a")))),CallExpr(Id("printf"),List(StringLiteral("""root1 = %.2lf and root2 = %.2lf"""),Id("root1"),Id("root2"))))),Some(If(BinaryOp("==",Id("determinant"),IntLiteral(0)),Block(List(),List(BinaryOp("=",Id("root1"),BinaryOp("=",Id("root2"),BinaryOp("/",UnaryOp("-",Id("b")),BinaryOp("*",IntLiteral(2),Id("a"))))),CallExpr(Id("printf"),List(StringLiteral("""root1 = root2 = %.2lf;"""),Id("root1"))))),Some(Block(List(),List(BinaryOp("=",Id("realPart"),BinaryOp("/",UnaryOp("-",Id("b")),BinaryOp("*",IntLiteral(2),Id("a")))),BinaryOp("=",Id("imaginaryPart"),BinaryOp("/",CallExpr(Id("sqrt"),List(UnaryOp("-",Id("determinant")))),BinaryOp("*",IntLiteral(2),Id("a")))),CallExpr(Id("printf"),List(StringLiteral("""root1 = %.2lf+%.2lfi and root2 = %.2f-%.2fi"""),Id("realPart"),Id("imaginaryPart"),Id("realPart"),Id("imaginaryPart"))))))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,270))
  }
  test("test complex statement 71") {
    val input = """
	//float n1, n2, n3;

	int main()
	{
		int year;

		printf("Enter a year: ");
		scanf("%d",year);

		if(year / 4 == 0)
		{
			if( year / 100 == 0)
			{
				// year is divisible by 400, hence the year is a leap year
				if ( year / 400 == 0)
					printf("%d is a leap year.", year);
				else
					printf("%d is not a leap year.", year);
			}
			else
				printf("%d is a leap year.", year );
		}
		else
			printf("%d is not a leap year.", year);
		
		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("year"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter a year: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("year"))),If(BinaryOp("==",BinaryOp("/",Id("year"),IntLiteral(4)),IntLiteral(0)),Block(List(),List(If(BinaryOp("==",BinaryOp("/",Id("year"),IntLiteral(100)),IntLiteral(0)),Block(List(),List(If(BinaryOp("==",BinaryOp("/",Id("year"),IntLiteral(400)),IntLiteral(0)),CallExpr(Id("printf"),List(StringLiteral("""%d is a leap year."""),Id("year"))),Some(CallExpr(Id("printf"),List(StringLiteral("""%d is not a leap year."""),Id("year"))))))),Some(CallExpr(Id("printf"),List(StringLiteral("""%d is a leap year."""),Id("year"))))))),Some(CallExpr(Id("printf"),List(StringLiteral("""%d is not a leap year."""),Id("year"))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,271))
  }
  test("test complex program 72") {
    val input = """
	/*
	#include <stdio.h>
	*/

	int main()
	{
		float number;

		printf("Enter a number: ");
		scanf("%lf", number);

		if (number <= 0.0)
		{
			if (number == 0.0)
				printf("You entered 0.");
			else
				printf("You entered a negative number.");
		}
		else
			printf("You entered a positive number.");
		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("number"),FloatType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter a number: """))),CallExpr(Id("scanf"),List(StringLiteral("""%lf"""),Id("number"))),If(BinaryOp("<=",Id("number"),FloatLiteral(0.0f)),Block(List(),List(If(BinaryOp("==",Id("number"),FloatLiteral(0.0f)),CallExpr(Id("printf"),List(StringLiteral("You entered 0."))),Some(CallExpr(Id("printf"),List(StringLiteral("You entered a negative number."))))))),Some(CallExpr(Id("printf"),List(StringLiteral("You entered a positive number."))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,272))
  }
  test("test complex program 73") {
    val input = """
	int n, i;

	// main function
	int main()
	{
		
		float factorial;
		factorial = 1;

		printf("Enter an integer: ");
		scanf("%d",n);

		// show error if the user enters a negative integer
		if (n < 0)
			printf("Error! Factorial of a negative number doesn't exist.");
		else
		{
			for(i = 1; i <= n; i = i + 1)
			{
				factorial =  factorial * i;              // factorial = factorial*i;
			}
			printf("Factorial of %d = %llu", n, factorial);
		}

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("n"),IntType),VarDecl(Id("i"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("factorial"),FloatType)),List(BinaryOp("=",Id("factorial"),IntLiteral(1)),CallExpr(Id("printf"),List(StringLiteral("""Enter an integer: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("n"))),If(BinaryOp("<",Id("n"),IntLiteral(0)),CallExpr(Id("printf"),List(StringLiteral("""Error! Factorial of a negative number doesn't exist."""))),Some(Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<=",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(BinaryOp("=",Id("factorial"),BinaryOp("*",Id("factorial"),Id("i")))))),CallExpr(Id("printf"),List(StringLiteral("""Factorial of %d = %llu"""),Id("n"),Id("factorial"))))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,273))
  }
  test("test complex program 74") {
    val input = """
	int n1, n2, i, gcd;

	int main()
	{
		
		printf("Enter two integers: ");
		scanf("%d %d", n1, n2);

		for(i=1; i <= n1 && i <= n2; i = i + 1)
		{
			// Checks if i is factor of both integers
			if(n1/i==0 && n2/i==0)
				gcd = i;
		}

		printf("G.C.D of %d and %d is %d", n1, n2, gcd);

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("n1"),IntType),VarDecl(Id("n2"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("gcd"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Enter two integers: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d %d"""),Id("n1"),Id("n2"))),For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("&&",BinaryOp("<=",Id("i"),Id("n1")),BinaryOp("<=",Id("i"),Id("n2"))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("&&",BinaryOp("==",BinaryOp("/",Id("n1"),Id("i")),IntLiteral(0)),BinaryOp("==",BinaryOp("/",Id("n2"),Id("i")),IntLiteral(0))),BinaryOp("=",Id("gcd"),Id("i")),None)))),CallExpr(Id("printf"),List(StringLiteral("""G.C.D of %d and %d is %d"""),Id("n1"),Id("n2"),Id("gcd"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,274))
  }
  test("test complex program 75") {
    val input = """
	int base, exponent;

	int main()
	{
		printf("Enter a base number: ");
		scanf("%d", base);

		printf("Enter an exponent: ");
		scanf("%d", exponent);

		do
			result = base;
			--exponent;
		while (exponent != 0);

		printf("Answer = %lld", result);

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("base"),IntType),VarDecl(Id("exponent"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Enter a base number: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("base"))),CallExpr(Id("printf"),List(StringLiteral("""Enter an exponent: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("exponent"))),Dowhile(List(BinaryOp("=",Id("result"),Id("base")),UnaryOp("-",UnaryOp("-",Id("exponent")))),BinaryOp("!=",Id("exponent"),IntLiteral(0))),CallExpr(Id("printf"),List(StringLiteral("""Answer = %lld"""),Id("result"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,275))
  }
  test("test complex program 76") {
    val input = """
	int low, high, i, flag;

	int main()
	{
		printf("Enter two numbers(intervals): ");
		scanf("%d %d", low, high);

		printf("Prime numbers between %d and %d are: ", low, high);

		do
			flag = 0;
			for(i = 2; i <= low / 2; i = i + 1)
			{
				if(low / i == 0)
				{
					flag = 1;
					break;
				}
			}

			if (flag == 0)
				printf("%d ", low);

			low = low + 1;
		while (low < high);

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("low"),IntType),VarDecl(Id("high"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("flag"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Enter two numbers(intervals): """))),CallExpr(Id("scanf"),List(StringLiteral("""%d %d"""),Id("low"),Id("high"))),CallExpr(Id("printf"),List(StringLiteral("""Prime numbers between %d and %d are: """),Id("low"),Id("high"))),Dowhile(List(BinaryOp("=",Id("flag"),IntLiteral(0)),For(BinaryOp("=",Id("i"),IntLiteral(2)),BinaryOp("<=",Id("i"),BinaryOp("/",Id("low"),IntLiteral(2))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",BinaryOp("/",Id("low"),Id("i")),IntLiteral(0)),Block(List(),List(BinaryOp("=",Id("flag"),IntLiteral(1)),Break)),None)))),If(BinaryOp("==",Id("flag"),IntLiteral(0)),CallExpr(Id("printf"),List(StringLiteral("""%d """),Id("low"))),None),BinaryOp("=",Id("low"),BinaryOp("+",Id("low"),IntLiteral(1)))),BinaryOp("<",Id("low"),Id("high"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,276))
  }
  test("test complex program 77") {
    val input = """
	int checkPrime(int n){}
	int main()
	{
		int n, i, flag;

		printf("Enter a positive integer: ");
		scanf("%d", n);

		for(i = 2; i<= n/2; i = i + 1)
		{
			// condition for i to be a prime number
			if (checkPrime(i) == 1)
			{
				// condition for n-i to be a prime number
				if (checkPrime(n-i) == 1)
				{
					// n = primeNumber1 + primeNumber2
					printf("%d = %d + %d\n", n, i, n - i);
					flag = 1;
				}

			}
		}

		if (flag == 0)
			printf("%d cannot be expressed as the sum of two prime numbers.", n);

		return 0;
	}

	// Function to check prime number
	int checkPrime(int n)
	{
		int i, isPrime;

		for(i = 2; i <= n/2; i = i + 1)
		{
			if(n / i == 0)
			{
				isPrime = 0;
				break;
			}  
		}

		return isPrime;
	}

    """
    val expected = Program(List(FuncDecl(Id("checkPrime"),List(VarDecl(Id("n"),IntType)),IntType,Block(List(),List())),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("n"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("flag"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter a positive integer: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("n"))),For(BinaryOp("=",Id("i"),IntLiteral(2)),BinaryOp("<=",Id("i"),BinaryOp("/",Id("n"),IntLiteral(2))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",CallExpr(Id("checkPrime"),List(Id("i"))),IntLiteral(1)),Block(List(),List(If(BinaryOp("==",CallExpr(Id("checkPrime"),List(BinaryOp("-",Id("n"),Id("i")))),IntLiteral(1)),Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""%d = %d + %d\n"""),Id("n"),Id("i"),BinaryOp("-",Id("n"),Id("i")))),BinaryOp("=",Id("flag"),IntLiteral(1)))),None))),None)))),If(BinaryOp("==",Id("flag"),IntLiteral(0)),CallExpr(Id("printf"),List(StringLiteral("""%d cannot be expressed as the sum of two prime numbers."""),Id("n"))),None),Return(Some(IntLiteral(0)))))),FuncDecl(Id("checkPrime"),List(VarDecl(Id("n"),IntType)),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("isPrime"),IntType)),List(For(BinaryOp("=",Id("i"),IntLiteral(2)),BinaryOp("<=",Id("i"),BinaryOp("/",Id("n"),IntLiteral(2))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",BinaryOp("/",Id("n"),Id("i")),IntLiteral(0)),Block(List(),List(BinaryOp("=",Id("isPrime"),IntLiteral(0)),Break)),None)))),Return(Some(Id("isPrime"))))))))
    assert(checkAst(input,expected,277))
  }
  test("test complex program 78") {
    val input = """
	int multiplyNumbers(int n){}

	int main()
	{
		int n;
		printf("Enter a positive integer: ");
		scanf("%d", n);
		printf("Factorial of %d = %ld", n, multiplyNumbers(n));
		return 0;
	}

	int multiplyNumbers(int n)
	{
		if (n >= 1)
			return n * multiplyNumbers(n-1);
		else
			return 1;
	}
    """
    val expected = Program(List(FuncDecl(Id("multiplyNumbers"),List(VarDecl(Id("n"),IntType)),IntType,Block(List(),List())),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("n"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter a positive integer: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("n"))),CallExpr(Id("printf"),List(StringLiteral("""Factorial of %d = %ld"""),Id("n"),CallExpr(Id("multiplyNumbers"),List(Id("n"))))),Return(Some(IntLiteral(0)))))),FuncDecl(Id("multiplyNumbers"),List(VarDecl(Id("n"),IntType)),IntType,Block(List(),List(If(BinaryOp(">=",Id("n"),IntLiteral(1)),Return(Some(BinaryOp("*",Id("n"),CallExpr(Id("multiplyNumbers"),List(BinaryOp("-",Id("n"),IntLiteral(1))))))),Some(Return(Some(IntLiteral(1))))))))))
    assert(checkAst(input,expected,278))
  }
  test("test complex program 79") {
    val input = """
	int hcf(int n1, int n2){}

	int main()
	{
	   int n1, n2;
	   printf("Enter two positive integers: ");
	   scanf("%d %d", n1, n2);

	   printf("G.C.D of %d and %d is %d.", n1, n2, hcf(n1,n2));
	   return 0;
	}

	int hcf(int n1, int n2)
	{
		if (n2 != 0)
		   return hcf(n2, n1/n2);
		else 
		   return n1;
	}

    """
    val expected = Program(List(FuncDecl(Id("hcf"),List(VarDecl(Id("n1"),IntType),VarDecl(Id("n2"),IntType)),IntType,Block(List(),List())),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("n1"),IntType),VarDecl(Id("n2"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter two positive integers: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d %d"""),Id("n1"),Id("n2"))),CallExpr(Id("printf"),List(StringLiteral("""G.C.D of %d and %d is %d."""),Id("n1"),Id("n2"),CallExpr(Id("hcf"),List(Id("n1"),Id("n2"))))),Return(Some(IntLiteral(0)))))),FuncDecl(Id("hcf"),List(VarDecl(Id("n1"),IntType),VarDecl(Id("n2"),IntType)),IntType,Block(List(),List(If(BinaryOp("!=",Id("n2"),IntLiteral(0)),Return(Some(CallExpr(Id("hcf"),List(Id("n2"),BinaryOp("/",Id("n1"),Id("n2")))))),Some(Return(Some(Id("n1"))))))))))
    assert(checkAst(input,expected,279))
  }
  test("test complex program 80") {
    val input = """
	int main() {
		int fp;
		boolean c;
		(5 > 3) * (3 > 2);
		fp = fopen(__FILE__, "r");
		do
			c = getc(fp);
			putchar(c);
		while (c != EOF);
		
		fclose(fp);
		return 0;
	}

    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("fp"),IntType),VarDecl(Id("c"),BoolType)),List(BinaryOp("*",BinaryOp(">",IntLiteral(5),IntLiteral(3)),BinaryOp(">",IntLiteral(3),IntLiteral(2))),BinaryOp("=",Id("fp"),CallExpr(Id("fopen"),List(Id("__FILE__"),StringLiteral("""r""")))),Dowhile(List(BinaryOp("=",Id("c"),CallExpr(Id("getc"),List(Id("fp")))),CallExpr(Id("putchar"),List(Id("c")))),BinaryOp("!=",Id("c"),Id("EOF"))),CallExpr(Id("fclose"),List(Id("fp"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,280))
  }
  test("test for statement 81") {
    val input = """
	int global;

	int main(int argc, int argv){
		int i, j;
		int sum;
		sum = 0;
		for(i = 0; i < 100; i = i + 1){
			for(j = 0; j < 100; j = j + 1)
				sum = sum + 1;
			if(sum == 102)
				break;
		}
	}
    """
    val expected = Program(List(VarDecl(Id("global"),IntType),FuncDecl(Id("main"),List(VarDecl(Id("argc"),IntType),VarDecl(Id("argv"),IntType)),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType),VarDecl(Id("sum"),IntType)),List(BinaryOp("=",Id("sum"),IntLiteral(0)),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),IntLiteral(100)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),IntLiteral(100)),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),BinaryOp("=",Id("sum"),BinaryOp("+",Id("sum"),IntLiteral(1)))),If(BinaryOp("==",Id("sum"),IntLiteral(102)),Break,None)))))))))
    assert(checkAst(input,expected,281))
  }
  test("test for statement 82") {
    val input = """
	int main()
	{
		int num, count, sum;
		sum = 0;

		printf("Enter a positive integer: ");
		scanf("%d", num);

		// for loop terminates when n is less than count
		for(count = 1; count <= num; count = count + 1)
		{
			sum = sum + count;
			if(sum == 100)
				break;
			else{
				for(num = 1; num < 50; num = num + 1)
					sum = sum + 2 * count;
			}
		}

		printf("Sum = %d", sum);

		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("num"),IntType),VarDecl(Id("count"),IntType),VarDecl(Id("sum"),IntType)),List(BinaryOp("=",Id("sum"),IntLiteral(0)),CallExpr(Id("printf"),List(StringLiteral("""Enter a positive integer: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("num"))),For(BinaryOp("=",Id("count"),IntLiteral(1)),BinaryOp("<=",Id("count"),Id("num")),BinaryOp("=",Id("count"),BinaryOp("+",Id("count"),IntLiteral(1))),Block(List(),List(BinaryOp("=",Id("sum"),BinaryOp("+",Id("sum"),Id("count"))),If(BinaryOp("==",Id("sum"),IntLiteral(100)),Break,Some(Block(List(),List(For(BinaryOp("=",Id("num"),IntLiteral(1)),BinaryOp("<",Id("num"),IntLiteral(50)),BinaryOp("=",Id("num"),BinaryOp("+",Id("num"),IntLiteral(1))),BinaryOp("=",Id("sum"),BinaryOp("+",Id("sum"),BinaryOp("*",IntLiteral(2),Id("count")))))))))))),CallExpr(Id("printf"),List(StringLiteral("""Sum = %d"""),Id("sum"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,282))
  }
  test("test for statement 83") {
    val input = """
	int number, i, j;

	int main()
	{
		printf("Enter a positive integer: ");
		scanf("%d", number);

		printf("Factors of %d are: ", number);
		for(i = 1; i <= number; i = i + 1)
		{
			if (number / i == 0){
				printf("%d ",i);
				for(j = 0; j < 50; j = j + 1)
					number = 2 * j;
			}
			else
				break;
		}

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("number"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Enter a positive integer: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("number"))),CallExpr(Id("printf"),List(StringLiteral("""Factors of %d are: """),Id("number"))),For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<=",Id("i"),Id("number")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",BinaryOp("/",Id("number"),Id("i")),IntLiteral(0)),Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""%d """),Id("i"))),For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),IntLiteral(50)),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),BinaryOp("=",Id("number"),BinaryOp("*",IntLiteral(2),Id("j")))))),Some(Break))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,283))
  }
  test("test for statement 84") {
    val input = """
	int main () {

	   /* local variable definition */
	   int i, j;
	   
	   for(i = 2; i < 100; i = i + 1) {
		  for(j = 2; j <= (i/j); j = j + 1){
			if(!(i*j))
				break;
				// if factor found, not prime
			if(j > (i/j))
				printf("%d is prime", i);
		  }
	   }
	 
	   return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType)),List(For(BinaryOp("=",Id("i"),IntLiteral(2)),BinaryOp("<",Id("i"),IntLiteral(100)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(For(BinaryOp("=",Id("j"),IntLiteral(2)),BinaryOp("<=",Id("j"),BinaryOp("/",Id("i"),Id("j"))),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),Block(List(),List(If(UnaryOp("!",BinaryOp("*",Id("i"),Id("j"))),Break,None),If(BinaryOp(">",Id("j"),BinaryOp("/",Id("i"),Id("j"))),CallExpr(Id("printf"),List(StringLiteral("""%d is prime"""),Id("i"))),None))))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,284))
  }
  test("test for statement 85") {
    val input = """
	int main()
	{
		int rows, coef, space, i, j;
		coef = 1;

		printf("Enter number of rows: ");
		scanf("%d",rows);

		for(i = 0; i < rows; i = i + 1)
		{
			for(space = 1; space <= rows - i; space = space + 1)
				printf(" ");

			for(j = 0; j <= i; j = j + 1)
			{
				if (j == 0 || i == 0)
					coef = 1;
				else
					coef = coef * (i - j + 1) / j;

				printf("%4d", coef);
			}
			printf("\n");

			if (coef != 1)
				break;
		}

		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("rows"),IntType),VarDecl(Id("coef"),IntType),VarDecl(Id("space"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType)),List(BinaryOp("=",Id("coef"),IntLiteral(1)),CallExpr(Id("printf"),List(StringLiteral("""Enter number of rows: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("rows"))),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("rows")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(For(BinaryOp("=",Id("space"),IntLiteral(1)),BinaryOp("<=",Id("space"),BinaryOp("-",Id("rows"),Id("i"))),BinaryOp("=",Id("space"),BinaryOp("+",Id("space"),IntLiteral(1))),CallExpr(Id("printf"),List(StringLiteral(""" """)))),For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<=",Id("j"),Id("i")),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),Block(List(),List(If(BinaryOp("||",BinaryOp("==",Id("j"),IntLiteral(0)),BinaryOp("==",Id("i"),IntLiteral(0))),BinaryOp("=",Id("coef"),IntLiteral(1)),Some(BinaryOp("=",Id("coef"),BinaryOp("/",BinaryOp("*",Id("coef"),BinaryOp("+",BinaryOp("-",Id("i"),Id("j")),IntLiteral(1))),Id("j"))))),CallExpr(Id("printf"),List(StringLiteral("""%4d"""),Id("coef")))))),CallExpr(Id("printf"),List(StringLiteral("""\n"""))),If(BinaryOp("!=",Id("coef"),IntLiteral(1)),Break,None)))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,285))
  }
  test("test complex program 86") {
    val input = """
	int n;
	int i, sum;

	int main(){
	  i= 1;
	  sum=0;

	  printf("Enter a number: ");
	  scanf("%d",n);

	  if(n/i == 0){
		sum = sum + i;
		i = i + 1;
		if(sum == n)
		  printf("%d is a perfect number",i);
		else
		  printf("%d is not a perfect number",i);
	  }
	  
	  return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("n"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("sum"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("=",Id("sum"),IntLiteral(0)),CallExpr(Id("printf"),List(StringLiteral("""Enter a number: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("n"))),If(BinaryOp("==",BinaryOp("/",Id("n"),Id("i")),IntLiteral(0)),Block(List(),List(BinaryOp("=",Id("sum"),BinaryOp("+",Id("sum"),Id("i"))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),If(BinaryOp("==",Id("sum"),Id("n")),CallExpr(Id("printf"),List(StringLiteral("""%d is a perfect number"""),Id("i"))),Some(CallExpr(Id("printf"),List(StringLiteral("""%d is not a perfect number"""),Id("i"))))))),None),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,286))
  }
  test("test complex program 87") {
    val input = """
	int main(){
		int num;
		num = 1;

		print(num);

		return 0;
	}

	int print(int num){
		if(num <= 100){
			printf("%d ",num);
			print(num + 1);
			if(num == 50)
				num = num + 2;
			else
				num = num + 3;
		}
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("num"),IntType)),List(BinaryOp("=",Id("num"),IntLiteral(1)),CallExpr(Id("print"),List(Id("num"))),Return(Some(IntLiteral(0)))))),FuncDecl(Id("print"),List(VarDecl(Id("num"),IntType)),IntType,Block(List(),List(If(BinaryOp("<=",Id("num"),IntLiteral(100)),Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""%d """),Id("num"))),CallExpr(Id("print"),List(BinaryOp("+",Id("num"),IntLiteral(1)))),If(BinaryOp("==",Id("num"),IntLiteral(50)),BinaryOp("=",Id("num"),BinaryOp("+",Id("num"),IntLiteral(2))),Some(BinaryOp("=",Id("num"),BinaryOp("+",Id("num"),IntLiteral(3))))))),None))))))
    assert(checkAst(input,expected,287))
  }
  test("test complex program 88") {
    val input = """
	float str[10];
	int intValue;

	int stringToInt(float str[]){
		int i, sum;
		i = sum = 0;

		if (str[i] != "0"){
			 if(str[i] < 48 || str[i] > 57){
				printf("Unable to convert it into integer.\n");
				return 0;
			 }
			 else{
				 sum = sum * 10 + (str[i] - 48);
				 i = i + 1;
			 }
		}
		return sum;
	}

	int main(){

		printf("Enter any integer as a string: ");
		scanf("%s",str);

		intValue = stringToInt(str);

		printf("Equivalent integer value: %d", intValue);

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("str"),ArrayType(IntLiteral(10),FloatType)),VarDecl(Id("intValue"),IntType),FuncDecl(Id("stringToInt"),List(VarDecl(Id("str"),ArrayPointerType(FloatType))),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("sum"),IntType)),List(BinaryOp("=",Id("i"),BinaryOp("=",Id("sum"),IntLiteral(0))),If(BinaryOp("!=",ArrayCell(Id("str"),Id("i")),StringLiteral("""0""")),Block(List(),List(If(BinaryOp("||",BinaryOp("<",ArrayCell(Id("str"),Id("i")),IntLiteral(48)),BinaryOp(">",ArrayCell(Id("str"),Id("i")),IntLiteral(57))),Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Unable to convert it into integer.\n"""))),Return(Some(IntLiteral(0))))),Some(Block(List(),List(BinaryOp("=",Id("sum"),BinaryOp("+",BinaryOp("*",Id("sum"),IntLiteral(10)),BinaryOp("-",ArrayCell(Id("str"),Id("i")),IntLiteral(48)))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))))))))),None),Return(Some(Id("sum")))))),FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Enter any integer as a string: """))),CallExpr(Id("scanf"),List(StringLiteral("""%s"""),Id("str"))),BinaryOp("=",Id("intValue"),CallExpr(Id("stringToInt"),List(Id("str")))),CallExpr(Id("printf"),List(StringLiteral("""Equivalent integer value: %d"""),Id("intValue"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,288))
  }
  test("test if statement 89") {
    val input = """
	int main()
	{
		float n1, n2, n3;

		printf("Enter three numbers: ");
		scanf("%lf %lf %lf", n1, n2, n3);

		if (n1 >= n2)
		{
			if(n1 >= n3)
				printf("%.2lf is the largest number.", n1);
			else
				printf("%.2lf is the largest number.", n3);
		}
		else
		{
			if(n2 >= n3)
				printf("%.2lf is the largest number.", n2);
			else
				printf("%.2lf is the largest number.", n3);
		}
		
		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("n1"),FloatType),VarDecl(Id("n2"),FloatType),VarDecl(Id("n3"),FloatType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter three numbers: """))),CallExpr(Id("scanf"),List(StringLiteral("""%lf %lf %lf"""),Id("n1"),Id("n2"),Id("n3"))),If(BinaryOp(">=",Id("n1"),Id("n2")),Block(List(),List(If(BinaryOp(">=",Id("n1"),Id("n3")),CallExpr(Id("printf"),List(StringLiteral("""%.2lf is the largest number."""),Id("n1"))),Some(CallExpr(Id("printf"),List(StringLiteral("""%.2lf is the largest number."""),Id("n3"))))))),Some(Block(List(),List(If(BinaryOp(">=",Id("n2"),Id("n3")),CallExpr(Id("printf"),List(StringLiteral("""%.2lf is the largest number."""),Id("n2"))),Some(CallExpr(Id("printf"),List(StringLiteral("""%.2lf is the largest number."""),Id("n3"))))))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,289))
  }
  test("test complex program 90") {
    val input = """
	int n, i, flag;
	boolean a, b[10];
	float c[5], d, e[10];

	int main()
	{
		flag = 0;

		printf("Enter a positive integer: ");
		scanf("%d", n);

		for(i = 2; i <= n/2; i = i + 1)
		{
			// condition for i to be a prime number
			if (checkPrime(i) == 1)
			{
				// condition for n-i to be a prime number
				if (checkPrime(n-i) == 1)
				{
					// n = primeNumber1 + primeNumber2
					printf("%d = %d + %d\n", n, i, n - i);
					flag = 1;
				}

			}
		}

		if (flag == 0)
			printf("%d cannot be expressed as the sum of two prime numbers.", n);

		return 0;
	}

	// Function to check prime number
	int checkPrime(int n)
	{
		int i, isPrime;
		isPrime = 1;

		for(i = 2; i <= n/2; i = i + 1)
		{
			if(n / i == 0)
			{
				isPrime = 0;
				break;
			}  
		}

		return isPrime;
	}
    """
    val expected = Program(List(VarDecl(Id("n"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("flag"),IntType),VarDecl(Id("a"),BoolType),VarDecl(Id("b"),ArrayType(IntLiteral(10),BoolType)),VarDecl(Id("c"),ArrayType(IntLiteral(5),FloatType)),VarDecl(Id("d"),FloatType),VarDecl(Id("e"),ArrayType(IntLiteral(10),FloatType)),FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("=",Id("flag"),IntLiteral(0)),CallExpr(Id("printf"),List(StringLiteral("""Enter a positive integer: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("n"))),For(BinaryOp("=",Id("i"),IntLiteral(2)),BinaryOp("<=",Id("i"),BinaryOp("/",Id("n"),IntLiteral(2))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",CallExpr(Id("checkPrime"),List(Id("i"))),IntLiteral(1)),Block(List(),List(If(BinaryOp("==",CallExpr(Id("checkPrime"),List(BinaryOp("-",Id("n"),Id("i")))),IntLiteral(1)),Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""%d = %d + %d\n"""),Id("n"),Id("i"),BinaryOp("-",Id("n"),Id("i")))),BinaryOp("=",Id("flag"),IntLiteral(1)))),None))),None)))),If(BinaryOp("==",Id("flag"),IntLiteral(0)),CallExpr(Id("printf"),List(StringLiteral("""%d cannot be expressed as the sum of two prime numbers."""),Id("n"))),None),Return(Some(IntLiteral(0)))))),FuncDecl(Id("checkPrime"),List(VarDecl(Id("n"),IntType)),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("isPrime"),IntType)),List(BinaryOp("=",Id("isPrime"),IntLiteral(1)),For(BinaryOp("=",Id("i"),IntLiteral(2)),BinaryOp("<=",Id("i"),BinaryOp("/",Id("n"),IntLiteral(2))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",BinaryOp("/",Id("n"),Id("i")),IntLiteral(0)),Block(List(),List(BinaryOp("=",Id("isPrime"),IntLiteral(0)),Break)),None)))),Return(Some(Id("isPrime"))))))))
    assert(checkAst(input,expected,290))
  }
  test("test Stringlit 91") {
    val input = """
	int main(){
		int number;
		int min, max;
	  
		printf("Enter the minimum range: ");
		scanf("%d",min);

		printf("Enter the maximum range: ");
		scanf("%d",max);

		printf("Odd numbers in given range are: ");
		for(number = min; number <= max; number = number + 1)
			printf("%d ", number);
			
		return 0;

	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("number"),IntType),VarDecl(Id("min"),IntType),VarDecl(Id("max"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter the minimum range: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("min"))),CallExpr(Id("printf"),List(StringLiteral("""Enter the maximum range: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("max"))),CallExpr(Id("printf"),List(StringLiteral("""Odd numbers in given range are: """))),For(BinaryOp("=",Id("number"),Id("min")),BinaryOp("<=",Id("number"),Id("max")),BinaryOp("=",Id("number"),BinaryOp("+",Id("number"),IntLiteral(1))),CallExpr(Id("printf"),List(StringLiteral("""%d """),Id("number")))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,291))
  }
  test("test complex program 92") {
    val input = """
	int num, r, sum, temp;
	int min, max;
	float arr[100];

	int main(){

		printf("Enter the minimum range: ");
		scanf("%d", min);

		printf("Enter the maximum range: ");
		scanf("%d", max);

		printf("Armstrong numbers in given range are: ");
		for(num = min; num <= max; num = num + 1){
			temp = num;
			sum = 0;

			for (temp; temp != 0; temp = temp / 10){
				 r = temp / 10;
				 sum=sum + (r * r * r);
			}
			if(sum == num)
				 printf("%d ", num);
		}

		return 0;
	}
    """
    val expected = Program(List(VarDecl(Id("num"),IntType),VarDecl(Id("r"),IntType),VarDecl(Id("sum"),IntType),VarDecl(Id("temp"),IntType),VarDecl(Id("min"),IntType),VarDecl(Id("max"),IntType),VarDecl(Id("arr"),ArrayType(IntLiteral(100),FloatType)),FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Enter the minimum range: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("min"))),CallExpr(Id("printf"),List(StringLiteral("""Enter the maximum range: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("max"))),CallExpr(Id("printf"),List(StringLiteral("""Armstrong numbers in given range are: """))),For(BinaryOp("=",Id("num"),Id("min")),BinaryOp("<=",Id("num"),Id("max")),BinaryOp("=",Id("num"),BinaryOp("+",Id("num"),IntLiteral(1))),Block(List(),List(BinaryOp("=",Id("temp"),Id("num")),BinaryOp("=",Id("sum"),IntLiteral(0)),For(Id("temp"),BinaryOp("!=",Id("temp"),IntLiteral(0)),BinaryOp("=",Id("temp"),BinaryOp("/",Id("temp"),IntLiteral(10))),Block(List(),List(BinaryOp("=",Id("r"),BinaryOp("/",Id("temp"),IntLiteral(10))),BinaryOp("=",Id("sum"),BinaryOp("+",Id("sum"),BinaryOp("*",BinaryOp("*",Id("r"),Id("r")),Id("r"))))))),If(BinaryOp("==",Id("sum"),Id("num")),CallExpr(Id("printf"),List(StringLiteral("""%d """),Id("num"))),None)))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,292))
  }
  test("test for statement 93") {
    val input = """
	int main(){
	  int r, i, j, k;
	  printf("Enter the number range: ");
	  scanf("%d", r);
	  for(i = 1; i <= r; i = i + 1){
		for(j = 1; j <= 10; j = j + 1)
			printf("%d*%d=%d ",i , j, i * j);
		printf("\n");
	  }
	  return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("r"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType),VarDecl(Id("k"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter the number range: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("r"))),For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<=",Id("i"),Id("r")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(For(BinaryOp("=",Id("j"),IntLiteral(1)),BinaryOp("<=",Id("j"),IntLiteral(10)),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),CallExpr(Id("printf"),List(StringLiteral("""%d*%d=%d """),Id("i"),Id("j"),BinaryOp("*",Id("i"),Id("j"))))),CallExpr(Id("printf"),List(StringLiteral("""\n""")))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,293))
  }
  test("test Stringlit 94") {
    val input = """
	int n,r,ncr;
	boolean t[5], s;
	float f, g[5];

	int main(){
	  printf("Enter any two numbers->");
	  scanf("%d %d", n, r);
	  ncr = fact(n) / ( fact(r) * fact(n-r));
	  printf("The NCR factor of %d and %d is %d", n, r, ncr);
	  return 0;
	}

	int fact(int n[]){
	  int i;
	  for(i = 0; i < n; i = i + 1){
		i = i * n;
		--n;
	  }

	  return i;
	}
    """
    val expected = Program(List(VarDecl(Id("n"),IntType),VarDecl(Id("r"),IntType),VarDecl(Id("ncr"),IntType),VarDecl(Id("t"),ArrayType(IntLiteral(5),BoolType)),VarDecl(Id("s"),BoolType),VarDecl(Id("f"),FloatType),VarDecl(Id("g"),ArrayType(IntLiteral(5),FloatType)),FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Enter any two numbers->"""))),CallExpr(Id("scanf"),List(StringLiteral("""%d %d"""),Id("n"),Id("r"))),BinaryOp("=",Id("ncr"),BinaryOp("/",CallExpr(Id("fact"),List(Id("n"))),BinaryOp("*",CallExpr(Id("fact"),List(Id("r"))),CallExpr(Id("fact"),List(BinaryOp("-",Id("n"),Id("r"))))))),CallExpr(Id("printf"),List(StringLiteral("""The NCR factor of %d and %d is %d"""),Id("n"),Id("r"),Id("ncr"))),Return(Some(IntLiteral(0)))))),FuncDecl(Id("fact"),List(VarDecl(Id("n"),ArrayPointerType(IntType))),IntType,Block(List(VarDecl(Id("i"),IntType)),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(BinaryOp("=",Id("i"),BinaryOp("*",Id("i"),Id("n"))),UnaryOp("-",UnaryOp("-",Id("n")))))),Return(Some(Id("i"))))))))
    assert(checkAst(input,expected,294))
  }
  test("test complex program 95") {
    val input = """
	int matrix[100];
	int i,j,r,c;
	float sum,product;

	int main()
	{
		printf("Enter number of Rows :");
		scanf("%d",r);
		printf("Enter number of Cols :");
		scanf("%d",c);
	 
		printf("\nEnter matrix elements :\n");
		for(i = 0; i < r; i = 1 + i)
		{
			for(j = 0; j < c; j = j + 1)
			{
				printf("Enter element [%d,%d] : ", i+1, j+1);
				scanf("%d",matrix[i]);
			}
		}
	 
		/*sum and product of all elements*/
		/*initializing sun and product variables*/
		sum = 0;
		product = 1;
		for(i = 0; i < r;i = i + 1)
		{
			for(j = 0; j < c; j = j + 1)
			{
				sum = matrix[i];
				product = matrix[j];
			}
		}
	 
		printf("\nSUM of all elements : %d \nProduct of all elements :%d", sum, product);
		return 0;   
	}
    """
    val expected = Program(List(VarDecl(Id("matrix"),ArrayType(IntLiteral(100),IntType)),VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType),VarDecl(Id("r"),IntType),VarDecl(Id("c"),IntType),VarDecl(Id("sum"),FloatType),VarDecl(Id("product"),FloatType),FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Enter number of Rows :"""))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("r"))),CallExpr(Id("printf"),List(StringLiteral("""Enter number of Cols :"""))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("c"))),CallExpr(Id("printf"),List(StringLiteral("""\nEnter matrix elements :\n"""))),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("r")),BinaryOp("=",Id("i"),BinaryOp("+",IntLiteral(1),Id("i"))),Block(List(),List(For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),Id("c")),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""Enter element [%d,%d] : """),BinaryOp("+",Id("i"),IntLiteral(1)),BinaryOp("+",Id("j"),IntLiteral(1)))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),ArrayCell(Id("matrix"),Id("i")))))))))),BinaryOp("=",Id("sum"),IntLiteral(0)),BinaryOp("=",Id("product"),IntLiteral(1)),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),Id("r")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),Id("c")),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),Block(List(),List(BinaryOp("=",Id("sum"),ArrayCell(Id("matrix"),Id("i"))),BinaryOp("=",Id("product"),ArrayCell(Id("matrix"),Id("j"))))))))),CallExpr(Id("printf"),List(StringLiteral("""\nSUM of all elements : %d \nProduct of all elements :%d"""),Id("sum"),Id("product"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,295))
  }
  test("test complex program 96") {
    val input = """
	int main()
	{
		int i,j;    /*Here, we will use i for outer loop counter
					  and j for inner loop counter*/
		int num;
	  
		for(i = 1; i <= 20; i = i + 1) /*to print table 1 to 20*/
		{
			/*each number has 10 multiples*/
			num= i;     /*to initialize number with i ( 1 to 20)*/
			for(j = 1; j <= 10; j = j + 1)
			{
				/*values will be padded with 3 spaces*/
				printf("%3d\t", (num * j));
				if(i == 10)
					continue;
			}
	  
			printf("\n"); /*after printing table of each number*/
		}
		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType),VarDecl(Id("num"),IntType)),List(For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<=",Id("i"),IntLiteral(20)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(BinaryOp("=",Id("num"),Id("i")),For(BinaryOp("=",Id("j"),IntLiteral(1)),BinaryOp("<=",Id("j"),IntLiteral(10)),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),Block(List(),List(CallExpr(Id("printf"),List(StringLiteral("""%3d\t"""),BinaryOp("*",Id("num"),Id("j")))),If(BinaryOp("==",Id("i"),IntLiteral(10)),Continue,None)))),CallExpr(Id("printf"),List(StringLiteral("""\n""")))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,296))
  }
  test("test complex program 97") {
    val input = """
	float factorial(int n)
	{
		int i, j;
		int fact;
		fact = 1;
	 
		if(n == 1)
			return fact;
	 
		for(i = n; i >= 1; --i){
			if(i < 0)
				continue;
			else{
				for(j = 0; j < 100; j = j + 1)
					fact = fact * i;

				if(j == 90)
					j = j + 2;
				else
					continue;
			}
		}
		return fact;
	}
	  
	int main()
	{
		int num;
	 
		printf("Enter an integer number :");
		scanf("%d", num);
	 
		printf("\nFactorial of %d is = %ld", num, factorial(num));
		 
		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("factorial"),List(VarDecl(Id("n"),IntType)),FloatType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType),VarDecl(Id("fact"),IntType)),List(BinaryOp("=",Id("fact"),IntLiteral(1)),If(BinaryOp("==",Id("n"),IntLiteral(1)),Return(Some(Id("fact"))),None),For(BinaryOp("=",Id("i"),Id("n")),BinaryOp(">=",Id("i"),IntLiteral(1)),UnaryOp("-",UnaryOp("-",Id("i"))),Block(List(),List(If(BinaryOp("<",Id("i"),IntLiteral(0)),Continue,Some(Block(List(),List(For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),IntLiteral(100)),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),BinaryOp("=",Id("fact"),BinaryOp("*",Id("fact"),Id("i")))),If(BinaryOp("==",Id("j"),IntLiteral(90)),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(2))),Some(Continue))))))))),Return(Some(Id("fact")))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("num"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter an integer number :"""))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("num"))),CallExpr(Id("printf"),List(StringLiteral("""\nFactorial of %d is = %ld"""),Id("num"),CallExpr(Id("factorial"),List(Id("num"))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,297))
  }
  test("test complex program 98") {
    val input = """
	int checkPrime(int num){
		int i;
		int flg;
		flg = 0;
		/*if number (num) is divisble by any number from 2 to num/2
		  number will not be prime.*/
		for(i = 2; i < (num - 1); i = i + 1)
		{
			if((num / i) == 0){
				flg = 1;
				break;
			}
		}
		if(flg)
			return 0;
		else
			return 1;
	}
	 
	int main()
	{
		int i, n;
	 
		printf("Enter the value of N: ");
		scanf("%d", n);
	 
		printf("All prime numbers are from 1 to %d:\n",n);
		for(i = 1;i <= n; i = i + 1)
		{
			if(checkPrime(i))
				printf("%d,",i);
			if(n == 10)
				continue;
			else
				break;
		}
		 
		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("checkPrime"),List(VarDecl(Id("num"),IntType)),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("flg"),IntType)),List(BinaryOp("=",Id("flg"),IntLiteral(0)),For(BinaryOp("=",Id("i"),IntLiteral(2)),BinaryOp("<",Id("i"),BinaryOp("-",Id("num"),IntLiteral(1))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",BinaryOp("/",Id("num"),Id("i")),IntLiteral(0)),Block(List(),List(BinaryOp("=",Id("flg"),IntLiteral(1)),Break)),None)))),If(Id("flg"),Return(Some(IntLiteral(0))),Some(Return(Some(IntLiteral(1)))))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("n"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter the value of N: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("n"))),CallExpr(Id("printf"),List(StringLiteral("""All prime numbers are from 1 to %d:\n"""),Id("n"))),For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<=",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(CallExpr(Id("checkPrime"),List(Id("i"))),CallExpr(Id("printf"),List(StringLiteral("""%d,"""),Id("i"))),None),If(BinaryOp("==",Id("n"),IntLiteral(10)),Continue,Some(Break))))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,298))
  }
  test("test complex program 99") {
    val input = """
	int main()
	{
		int age;
		int cnt_baby, cnt_school, cnt_adult;
		int count;
		float a[100];
		boolean flag;
		
		for(count = 0; count < 15; count = count + 1)
		{
			int i;
			printf("Enter age of person [%d]: ", count + 1);
			scanf("%d", age);
			
			if(age >= 0 && age <= 5){
				cnt_baby = cnt_baby + 1;
				for(i = 0; i < 100; i = i + 1){
					if(a[i] == 1)
						flag = 1;
					else
						flag = 0;
				}
			}
			
			if(age >= 6 && age <= 17)
				cnt_school = cnt_school + 1;
			else
				cnt_adult = cnt_adult + 1;
		}
		
		printf("Baby age: %d\n", cnt_baby);
		printf("School age: %d\n", cnt_school);
		printf("Adult age: %d\n", cnt_adult);
		
		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("age"),IntType),VarDecl(Id("cnt_baby"),IntType),VarDecl(Id("cnt_school"),IntType),VarDecl(Id("cnt_adult"),IntType),VarDecl(Id("count"),IntType),VarDecl(Id("a"),ArrayType(IntLiteral(100),FloatType)),VarDecl(Id("flag"),BoolType)),List(For(BinaryOp("=",Id("count"),IntLiteral(0)),BinaryOp("<",Id("count"),IntLiteral(15)),BinaryOp("=",Id("count"),BinaryOp("+",Id("count"),IntLiteral(1))),Block(List(VarDecl(Id("i"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter age of person [%d]: """),BinaryOp("+",Id("count"),IntLiteral(1)))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("age"))),If(BinaryOp("&&",BinaryOp(">=",Id("age"),IntLiteral(0)),BinaryOp("<=",Id("age"),IntLiteral(5))),Block(List(),List(BinaryOp("=",Id("cnt_baby"),BinaryOp("+",Id("cnt_baby"),IntLiteral(1))),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),IntLiteral(100)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("==",ArrayCell(Id("a"),Id("i")),IntLiteral(1)),BinaryOp("=",Id("flag"),IntLiteral(1)),Some(BinaryOp("=",Id("flag"),IntLiteral(0))))))))),None),If(BinaryOp("&&",BinaryOp(">=",Id("age"),IntLiteral(6)),BinaryOp("<=",Id("age"),IntLiteral(17))),BinaryOp("=",Id("cnt_school"),BinaryOp("+",Id("cnt_school"),IntLiteral(1))),Some(BinaryOp("=",Id("cnt_adult"),BinaryOp("+",Id("cnt_adult"),IntLiteral(1)))))))),CallExpr(Id("printf"),List(StringLiteral("""Baby age: %d\n"""),Id("cnt_baby"))),CallExpr(Id("printf"),List(StringLiteral("""School age: %d\n"""),Id("cnt_school"))),CallExpr(Id("printf"),List(StringLiteral("""Adult age: %d\n"""),Id("cnt_adult"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,299))
  }
  test("test complex program 100") {
    val input = """
	int checkLeapYear(int year)
	{
		if( (year / 400 == 0) || (year / 4 == 0 && year / 100 != 0) )
			return 1;
		else
			return 0;
	}
	 
	int main(int argc, float argv[])
	{
		int i, j, n;
	 
		printf("Enter the value of N: ");
		scanf("%d", n);
	 
		printf("Leap years from 1 to %d:\n", n);
		for(i = 1; i <= n; i = i + 1)
		{
			if(checkLeapYear(i))
			if(i == 2)
				printf("%d\t", i);
			else
				for(j = 1; j <= n; j = j + 1)
					checkLeapYear(j);
		}
		return 0;
	}
    """
    val expected = Program(List(FuncDecl(Id("checkLeapYear"),List(VarDecl(Id("year"),IntType)),IntType,Block(List(),List(If(BinaryOp("||",BinaryOp("==",BinaryOp("/",Id("year"),IntLiteral(400)),IntLiteral(0)),BinaryOp("&&",BinaryOp("==",BinaryOp("/",Id("year"),IntLiteral(4)),IntLiteral(0)),BinaryOp("!=",BinaryOp("/",Id("year"),IntLiteral(100)),IntLiteral(0)))),Return(Some(IntLiteral(1))),Some(Return(Some(IntLiteral(0)))))))),FuncDecl(Id("main"),List(VarDecl(Id("argc"),IntType),VarDecl(Id("argv"),ArrayPointerType(FloatType))),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType),VarDecl(Id("n"),IntType)),List(CallExpr(Id("printf"),List(StringLiteral("""Enter the value of N: """))),CallExpr(Id("scanf"),List(StringLiteral("""%d"""),Id("n"))),CallExpr(Id("printf"),List(StringLiteral("""Leap years from 1 to %d:\n"""),Id("n"))),For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<=",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(CallExpr(Id("checkLeapYear"),List(Id("i"))),If(BinaryOp("==",Id("i"),IntLiteral(2)),CallExpr(Id("printf"),List(StringLiteral("""%d\t"""),Id("i"))),Some(For(BinaryOp("=",Id("j"),IntLiteral(1)),BinaryOp("<=",Id("j"),Id("n")),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),CallExpr(Id("checkLeapYear"),List(Id("j")))))),None)))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,300))
  }
}
