import org.scalatest.FunSuite
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class AstSuite extends FunSuite with TestAst {
test("a simple program with void as return type of main") {
  val input = """
  int a, b;
  int main()
  {
    int a, b;
    swap(a,b);
    return 0;
  }
  """
  val expected = Program(List(VarDecl(Id("a"),IntType), VarDecl(Id("b"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType), VarDecl(Id("b"),IntType)),List(CallExpr(Id("swap"),List(Id("a"),Id("b"))),Return(Some(IntLiteral(0))))))))
  assert(checkAst(input,expected,201))
}
  test("another simple program with int as return type of main") {
    val input = "int main () {}"
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,202))
  }
  test("a simple program has a simple call putIntLn") {
    val input = "void main () {putIntLn(5);}"
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("putIntLn"),List(IntLiteral(5))))))))
    assert(checkAst(input,expected,203))
  }
  test("test dangling if") {
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
  test("test dangling if 1") {
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
  test("test dangling if 2") {
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
  test("test dangling if 3") {
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
  test("test dangling if 4") {
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
  test("test dangling if 5") {
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
  test("test dangling if6") {
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
  test("test dangling if7") {
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
  test("test dangling if8") {
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
  test("test dangling if9") {
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
  test("test dangling if10") {
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
  test("test dangling if11") {
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
  test("test dangling if12") {
    val input = """
    int main() {
        if (true) a = 1;
        else if (false) a = 2;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(BooleanLiteral(true),BinaryOp("=",Id("a"),IntLiteral(1)),Some(If(BooleanLiteral(false),BinaryOp("=",Id("a"),IntLiteral(2)),None))))))))
    assert(checkAst(input,expected,216))
  }
  test("test dangling if13") {
    val input = """
    int main() {
        if (a>2.1)
            if (b<-1)
                if (c == 2)
                    d = 4;
                    a = 2;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(BinaryOp(">",Id("a"),FloatLiteral(2.1f)),If(BinaryOp("<",Id("b"),IntLiteral(-1)),If(BinaryOp("==",Id("c"),IntLiteral(2)),BinaryOp("=",Id("d"),IntLiteral(4)),None),None),None),BinaryOp("=",Id("a"),IntLiteral(2)))))))
    assert(checkAst(input,expected,217))
  }
  test("test dangling if14") {
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
  test("test dangling if19") {
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
  test("test dangling if20") {
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
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,220))
  }
  test("test dangling if21") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,221))
  }
  test("test dangling if22") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,222))
  }
  test("test dangling if23") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,223))
  }
  test("test dangling if24") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,224))
  }
  test("test dangling if25") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,225))
  }
  test("test dangling if26") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,226))
  }
  test("test dangling if27") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,227))
  }
  test("test dangling if28") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,228))
  }
  test("test dangling if29") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,229))
  }
  test("test dangling if30") {
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
  test("test dangling if31") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,231))
  }
  test("test dangling if32") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,232))
  }
  test("test dangling if33") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,233))
  }
  test("test dangling if34") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,234))
  }
  test("test dangling if35") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,235))
  }
  test("test dangling if36") {
    val input = """
    int a;
    float b;
    boolean f1() {int c; int d; c=1; d=2; return c==d;}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),FloatType),FuncDecl(Id("f1"),List(),BoolType,Block(List(VarDecl(Id("c"),IntType),VarDecl(Id("d"),IntType)),List(BinaryOp("=",Id("c"),IntLiteral(1)),BinaryOp("=",Id("d"),IntLiteral(2)),Return(Some(BinaryOp("==",Id("c"),Id("d")))))))))
    assert(checkAst(input,expected,236))
  }
  test("test dangling if37") {
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
  test("test dangling if38") {
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
  test("test dangling if39") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,239))
  }
  test("test dangling if40") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,240))
  }
  test("test dangling if41") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,241))
  }
  test("test dangling if42") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,242))
  }
  test("test dangling if43") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,243))
  }
  test("test dangling if44") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,244))
  }
  test("test dangling if45") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,245))
  }
  test("test dangling if46") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,246))
  }
  test("test dangling if47") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,247))
  }
  test("test dangling if48") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,248))
  }
  test("test dangling if49") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,249))
  }
  test("test dangling if50") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,250))
  }
  test("test dangling if51") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,251))
  }
  test("test dangling if52") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,252))
  }
  test("test dangling if53") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,253))
  }
  test("test dangling if54") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,254))
  }
  test("test dangling if55") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,255))
  }
  test("test dangling if56") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,256))
  }
  test("test dangling if57") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,257))
  }
  test("test dangling if58") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,258))
  }
  test("test dangling if59") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,259))
  }
  test("test dangling if60") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,260))
  }
  test("test dangling if61") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,261))
  }
  test("test dangling if62") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,262))
  }
  test("test dangling if63") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,263))
  }
  test("test dangling if64") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,264))
  }
  test("test dangling if65") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,265))
  }
  test("test dangling if66") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,266))
  }
  test("test dangling if67") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,267))
  }
  test("test dangling if68") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,268))
  }
  test("test dangling if69") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,269))
  }
  test("test dangling if70") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,270))
  }
  test("test dangling if71") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,271))
  }
  test("test dangling 72") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,272))
  }
  test("test dangling 73") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,273))
  }
  test("test dangling if74") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,274))
  }
  test("test dangling if75") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,275))
  }
  test("test dangling if76") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,276))
  }
  test("test dangling if77") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,277))
  }
  test("test dangling if78") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,278))
  }
  test("test dangling i79f") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,279))
  }
  test("test dangling if80") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,280))
  }
  test("test dangling if81") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,281))
  }
  test("test dangling if82") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,282))
  }
  test("test dangling if83") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,283))
  }
  test("test dangling if84") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,284))
  }
  test("test dangling if85") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,285))
  }
  test("test dangling if86") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,286))
  }
  test("test dangling if87") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,287))
  }
  test("test dangling if88") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,288))
  }
  test("test dangling if89") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,289))
  }
  test("test dangling if90") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,290))
  }
  test("test dangling if91") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,291))
  }
  test("test dangling if92") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,292))
  }
  test("test dangling if93") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,293))
  }
  test("test dangling if94") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,294))
  }
  test("test dangling if95") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,295))
  }
  test("test dangling if96") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,296))
  }
  test("test dangling if97") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,297))
  }
  test("test dangling if98") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,298))
  }
  test("test dangling if99") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,299))
  }
  test("test dangling if100") {
    val input = """
    int main()
    {
      int a, b, c;
      c = tong(a, b);
      return 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),If(Id("b"),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("b"),Id("a")))),None))))))
    assert(checkAst(input,expected,100))
  }
}
