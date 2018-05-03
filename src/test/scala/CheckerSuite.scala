import org.scalatest.FunSuite
import mc.checker._
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class CheckerSuite extends FunSuite with TestChecker {
  test("Undeclared Function") {
    val input = "void main () {} void main () {}"
    val expected = "Redeclared Function: main"
    assert(checkCkr(input,expected,401))
  }
  test("Type Mismatch In Expression: getInt") {
    val input = "void main () {getInt(3);}"
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("getInt"),List(IntLiteral(3))).toString
    assert(checkCkr(input,expected,402))
  }
  test("Type Mismatch In Expression: putIntLn") {

    val input = "void main () {putIntLn();}"
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putIntLn"),List()).toString
    assert(checkCkr(input,expected,403))
  }
  test("Check with AST") {

    val input = Program(List(
      FuncDecl(Id("main"),List(),VoidType,
        Block(List(),
          List(CallExpr(Id("putIntLn"),List()))))))
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putIntLn"),List()).toString
    assert(checkAst(input,expected,404))
  }
  test("Check with AST putIntLn with float") {

    val input = Program(List(
      FuncDecl(Id("main"),List(),VoidType,
        Block(List(),
          List(CallExpr(Id("putIntLn"),List(FloatLiteral(1.2f))))))))
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putIntLn"),List(FloatLiteral(1.2f))).toString
    assert(checkAst(input,expected,405))
  }
  test("Undeclared Variable") {
    val input = "int a; int a; void main () {} void abc () {}"
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input,expected,406))
  }

  test("Undeclared Variable 2") {
    val input = "int a; void main (int b, int b) {} void abc () {}"
    val expected = "Redeclared Parameter: b"
    assert(checkCkr(input,expected,407))
  }
  test("Undeclared Variable 3") {
    val input = "int a; void main (int b, int c) {} void abc () {} int a;"
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input,expected,408))
  }
  
  test("Type Mismatch In Statement 11") {
    val input = "int a; void main (int b, int c) {return a;} void abc () {}"
    val expected = """Type Mismatch In Statement: Return(Some(Id("a")))"""
    assert(checkCkr(input,expected,409))
  }
  test("Function Not Return 12") {
    val input = """
    int a; 
    void main (int b, int c) {} 
    int abc () {}"""
    val expected = """Function Not Return: abc"""
    assert(checkCkr(input,expected,410))
  }
  test("Type Mismatch In Expression 13") {
    val input = """
    void foo(){
      return;
    }
    void main(){
      int x;
      boolean y;
      x = true; //error here
      return;
    }
    """
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("x"),BooleanLiteral(true))"""
    assert(checkCkr(input,expected,411))
  }
  test("Type Mismatch In Expression 14") {
    val input = """
    float divide(float n){
      n = n/2;
      return n;
    }
    void main(){
      int x;
      x = divide(5.2); //error here
      return;
    }
  """
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("x"),CallExpr(Id("divide"),List(FloatLiteral(5.2f))))"""
    assert(checkCkr(input,expected,412))
  }
  test("Type Mismatch In Expression 15") {
    val input = """
    void print(int x){
      return;
    }
    void main(){
      int x;
      float b;
      b=1.1;

      for(x = 1; x < 10; x = x+1){
        print(x);
      }

      for(x = 1; x < 10; x = x+b){ //Error here
        print(x);
      }

      return ;
    }
  """
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("x"),BinaryOp("+",Id("x"),Id("b")))"""
    assert(checkCkr(input,expected,413))
  }
  test("Type Mismatch In Expression 16") {
    val input = """
      void print(int x){
        return;
      }
      void main(){
        int x;
        boolean b;
        b=true;

        for(x = 1; x < 10; x = x+1){
          print(x);
        }

        for(x = 1; x < 10; x = x+b){ //Error here
          print(x);
        }

        return;
      }
      """
    val expected = """Type Mismatch In Expression: BinaryOp("+",Id("x"),Id("b"))"""
    assert(checkCkr(input,expected,414))
  }
  test("Function Not Return 17") {
    val input = """
    int x(){ 
      int a, b, c;
      a = 4;
      b = 5;
      c = a + b;
    }
    void main(){}
    """
    val expected = """Function Not Return: x"""
    assert(checkCkr(input,expected,415))
  }
  test("Function Not Return 18") {
    val input = """
    int test(){
      int a;
      a = 10;
      return a;
    }
    int test2(){
      int c;
      c = test();
    }"""
    val expected = """Function Not Return: test2"""
    assert(checkCkr(input,expected,416))
  }
  test("Break Not In Loop 19") {
    val input = """
    int compare(int a, int b){
      int result;
      if (a < b) result = a;
      else result = b;
      return result;
    }
    int program(){
      int x,y;
      y = 50;
      for (x = 1; x <= y; x = x + 1)
      {
        for (x = 1; x <= y; x = x + 1){
          if (compare(x, y) >= x)
          {
            break;
          }
          else continue;
        }
        break;
      }
      break;
    } 
    """
    val expected = """Break Not In Loop"""
    assert(checkCkr(input,expected,417))
  }
  test("Type Mismatch In Expression 110") {
    val input = """
    float notmain(){
      int a,b,c,d,e;
      a = 1;
      b = 2;
      c = a + b;
      d = b * c;
      e = c / d;
      return 1;
    }
    int definitelynotmain(){
      int k;
      k = 1000;
      return k;
    }
    void main(){
      int x,y;
      x = notmain();
      for (y = 0; y <= x; y = y + 1){
        definitelynotmain();
      }
    } """
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("x"),CallExpr(Id("notmain"),List()))"""
    assert(checkCkr(input,expected,418))
  }
  test("Type Mismatch In Statement 111") {
    val input = """
    // Short, Type Mismatch In If Statement
    void main(){
      int a;
      if(a = 0)
        a = a + 1;
      return;
    }
    """
    val expected = """Type Mismatch In Statement: If(BinaryOp("=",Id("a"),IntLiteral(0)),BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1))),None)"""
    assert(checkCkr(input,expected,419))
  }
  test("Type Mismatch In Statement 112") {
    val input = """
    //Long, Type Mismatch 

    int global_x, global_y;
    boolean global_z;

    void foo(int para_x){
      do
      {
        int local_x;
        local_x = global_y;
        global_z = true;
      }
      while (true);
      return;
    }

    void wrongFoo(int para_x){
      do
      {
        int local_x;
        local_x = global_y;
        global_z = true;
      }
      while(global_x - para_x = 0);
      return;
    }
    """
    val expected = """Type Mismatch In Statement: Dowhile(List(Block(List(VarDecl(Id("local_x"),IntType)),List(BinaryOp("=",Id("local_x"),Id("global_y")),BinaryOp("=",Id("global_z"),BooleanLiteral(true))))),BinaryOp("=",BinaryOp("-",Id("global_x"),Id("para_x")),IntLiteral(0)))"""
    assert(checkCkr(input,expected,420))
  }
  test("Type Mismatch In Statement 113") {
    val input = """
    boolean isOpposite(int para_x, int para_y){
      if(para_x*para_x == para_y*para_y)
        return true;
      else
        return false;
    }

    boolean isAnotherOpposite(int para_x, int para_y){
      if(para_x*para_x == para_y*para_y)
        return 1;	// wrong here
      else
        return false;
    }

    void main(){
      int local_x, local_y;
      local_x = 13;
      local_y = -13;
      if(isOpposite(local_x, local_y))
        isAnotherOpposite(local_y, local_x);
      return;
    }
    """
    val expected = """Type Mismatch In Statement: Return(Some(IntLiteral(1)))"""
    assert(checkCkr(input,expected,421))
  }
  test("Type Mismatch In Statement 114") {
    val input = """
    int x, y, z;
    void foo1(){
      int a;
      boolean b;
      a = 1;
      return;
    }

    void foo2(){
      int a;
      boolean b;
      a = 1;
      return false;
    }
    """
    val expected = """Type Mismatch In Statement: Return(Some(BooleanLiteral(false)))"""
    assert(checkCkr(input,expected,422))
  }
  test("cUndeclared Function 115") {
    val input = """
    int a;
    float b[4];

    void main() {
      foo(); 
    }
    """
    val expected = """Undeclared Function: foo"""
    assert(checkCkr(input,expected,423))
  }
  test("Undeclared Function 116") {
    val input = """
    int foo(){
	    int a, b;
      a= b= 4;	
      return b* 4+ a;	
    }
    void main(){
      int c;
      c= foo(); 
      foo1();
    }
    """
    val expected = """Undeclared Function: foo1"""
    assert(checkCkr(input,expected,424))
  }
  test("Undeclared Function 117") {
    val input = """
    int add(int a, int b){
      return a+ b;
    }

    int minus(int a, int b){
      return a- b;
    }

    void main(){
      int i, a, b[10];
      a= 10 - 1;
      i= 0;
      for(i; i < a; i= i+ 1){
        b[1]= add(a, b[10]);
        b[1]= sub(a+ 1, b[9]);
      }
      return;
    }
    """
    val expected = """Undeclared Function: sub"""
    assert(checkCkr(input,expected,425))
  }
  test("Undeclared Function 118") {
    val input = """
    float power(float num, int exp){
      if(exp == 0)
          return 1;
      else{
          if(exp == 1)
              return num;
          else
              return num*power(num, exp- 1);
      }
    }

    void main(){
      float a;
      int b;
      int c;

      a= 2.5;
      b= 4;
      c= 2;
      power(a, c);
      powerrrr(a, b);

      return;
    }
    """
    val expected = """Undeclared Function: powerrrr"""
    assert(checkCkr(input,expected,426))
  }
  test("Undeclared Function 119") {
    val input = """
    int fibonaccy(int index){
        if(index == 0)
            return 0;
        else{
            if(index == 1)
                return 1;
            else
                return fibonaccy(index-1)+fibonaccy(index-2);
        }
    }

    void main(){
      int i, k, num;
      i= k= 4;
      
      num= fibonaccy(i);
      fibonaccyy(k);
    }
    """
    val expected = """Undeclared Function: fibonaccyy"""
    assert(checkCkr(input,expected,427))
  }
  test("Type Mismatch In Expression 120") {
    val input = """
    int fibonaccy(int index){
        if(index == 0)
            return 0;
        else{
            if(index == 1)
                return 1;
            else
                return fibonaccy(index-1)+fibonaccy(index-2);
        }
    }

    void main(){
      int i, k, num;
      boolean u;
      i= k= 4;
      num = fibonaccy(i);
      fibonaccy(u);
    }
    """
    val expected = """Type Mismatch In Expression: CallExpr(Id("fibonaccy"),List(Id("u")))"""
    assert(checkCkr(input,expected,428))
  }
  test("Redeclared Parameter 121") {
    val input = """
    int foo(boolean b, boolean h){
      int f;
      f=5;
      if(f>5){
      int g;
      g=5;
      g = g+6;
      }
      return 0;
    }
    void main ( boolean hg, boolean hd){
      boolean hg;
      int wrong;
      return;
    }"""
    val expected = """Redeclared Variable: hg"""
    assert(checkCkr(input,expected,429))
  }
  test("Redeclared Variable 122") {
    val input = """
    int sum(int a, int b){
      int res;
      res = a + b;
      return res;
    }

    void main(){
      int a, b, c;
      boolean a; //redeclare variable 
      b = 9;
      c = 8;
      if(sum(c,b) < 20) c = c + 1;
      return;
    }"""
    val expected = """Redeclared Variable: a"""
    assert(checkCkr(input,expected,430))
  }
  test("Redeclared Variable 123") {
    val input = """
    int range(int a, int b){
      int i;
      int count;
      count = 0;
      for(i = a; i <= b; i = i + 1){
        float sum;
        int sum; //redeclare variable
        count = count + 1;
      }
      return count;
    }
    void main(){
      int a, b;
      a = 5;
      b = 10;
      return;
    }"""
    val expected = """Redeclared Variable: sum"""
    assert(checkCkr(input,expected,431))
  }
  test("No Entry Point 124") {
    val input = """
    int sum;
    //float sum; //redeclared variable

    void doNothing(){
      return;
    }

    boolean doSomething(int a){
      if (a < 5) a = a + 1;
      else if (a > 5) a = a * 2;
      else a = a / 5;
      return true;
    }

    int main(){
      int arr[6];
      int i, a;
      a = 0;
      for(i = 0; i < 6; i = i + 1) arr[i] = i;
      if (arr[0] == 1)
        if (arr[1] == 2)
          if (arr[2] == 3)
            if (arr[3] == 4)
              if (arr[4] == 5)
                if (arr[5] == 6)
                  a = a + 6;
                else a = a + 5;
              else a = a + 4;
            else a = a + 3;
          else a = a + 2;
        else a = a + 1;
      return 0;
    }"""
    val expected = """No Entry Point"""
    assert(checkCkr(input,expected,432))
  }
  test("Type Mismatch In Statement 125") {
    val input = """
    int return10(){
      int a;
      a = 10;
      return a;
    }

    int return100(){
      int a;
      a = return10()*return10();
      return a;
    }

    int return1000(){
      int a;
      a = return10()*return100();
      return 1.1;
    }

    void main(){
      int a;
      a = return1000();
      if (a > 100) {
        float b;
        int b; //redeclared variable
        a = a*return10();
      }
      return;
    }"""
    val expected = """Type Mismatch In Statement: Return(Some(FloatLiteral(1.1f)))"""
    assert(checkCkr(input,expected,433))
  }
  test("Type Mismatch In Expression 126") {
    val input = """
    float hehe(){ return 10.; }
    int short_testcase1(){
      int a;
      a = hehe();
      return 0;
    }"""
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("a"),CallExpr(Id("hehe"),List()))"""
    assert(checkCkr(input,expected,434))
  }
  test("Undeclared Identifier 127") {
    val input = """
    void malloc(int k){ return; }
    float short_testcase2(){
      int e[10];
      e[0] = 100;
      t = malloc(e[0]);
      return 1.e-5;
    }"""
    val expected = """Undeclared Identifier: t"""
    assert(checkCkr(input,expected,435))
  }
  test("Type Mismatch In Expression 128") {
    val input = """
    int shhh_correct(int a){
      a = a + 1000;
      return a;
    }

    int large_testcase1_correct(){
      int i;
      int j, k;
      i = shhh_correct(1);
      j = 3/2;
      for(i; j < 10; j = j + 1)
        k = j; 
      return 0;
    }


    int shhh(int a){
      a = a + 1000;
      return a;
    }

    int large_testcase1(){
      int i;
      int j, k;
      i = shhh(1);
      j = 3/2;
      for(i; j < 10; j = j + 1)
        k[i*j] = j; 
      return 0;
    }"""
    val expected = """Type Mismatch In Expression: ArrayCell(Id("k"),BinaryOp("*",Id("i"),Id("j")))"""
    assert(checkCkr(input,expected,436))
  }
  test("Continue Not In Loop 129") {
    val input = """
    // Testcase 1 - Short testcase: Continue not in loop
    void main() {
      int i;
      int j;	
      i = 10;	
      for (j = 0; j < i; j = j + 1) {}
      continue; // Continue not in loop
      return;
    }"""
    val expected = """Continue Not In Loop"""
    assert(checkCkr(input,expected,437))
  }
  test("Continue Not In Loop 130") {
    val input = """
    // Testcase 2 - Short testcase: Continue not in loop
    void doNothing() {
      int i;
      for (i = 0; i == 5; i = 5) { return; }	
    }
    void main() {
      doNothing();
      continue; // Continue not in loop
      return;
    }"""
    val expected = """Continue Not In Loop"""
    assert(checkCkr(input,expected,438))
  }
  test("Continue Not In Loop 131") {
    val input = """
    // Testcase 3 - Long testcase: Continue not in loop
    float doSomethingRedundant() {
      int i;
      int j;
      float k;
      
      i = 10 * 10 + 10 / 10;
      j = (i - 101) * 1;
      k = 69.0;
      for (i = 0; i < 1007 - 1000; i = i * 2 + 1) { 
        j + 1; 
        continue; 
      }	
      return k * j;
    }
    void main() {
      float _someFloat;
      
      _someFloat = doSomethingRedundant();
      continue; // Continue not in loop
      return;
    }"""
    val expected = """Continue Not In Loop"""
    assert(checkCkr(input,expected,439))
  }
  test("Continue Not In Loop 132") {
    val input = """
    // Testcase 4 - Long testcase: Continue not in loop
    float fa;
    void doNothingLiterally() {
      return;
    }
    float doSomethingWithParemeter(int p0, float p1, boolean p2) {
      for (p0; p2 != false; p0 = p0 * 2) {
        p1 = 1.0;
        for (p0; p2 == true && p0 < 100; p0) {
          continue;
        }
        if (p0 == 64) {
          //p2 = true;
        }
      }
      return p1;
    }
    void main() {
      int i;
      doSomethingWithParemeter(0, fa, true);
      do
        continue;
      while (true);
      continue; // Continue not in loop
      i = 0;
      return;
    }"""
    val expected = """Continue Not In Loop"""
    assert(checkCkr(input,expected,440))
  }
  test("Continue Not In Loop 133") {
    val input = """
    // Testcase 5 - Long testcase: Continue not in loop
    int a;
    float b;
    boolean c;
    int arr_a[100];
    float arr_b[80];
    boolean arr_c[60];

    void initIntArray(int size, int arr) {
      int i;
      
      i = 0;
      do
        arr = 0;
        i = i + 1;
        continue;
      while (i >= size);
      continue; // Continue not in loop
      return;
    }
    void main() {
      int intArr;
      float floatArr[10];
      
      initIntArray(100, intArr);
      for (a; a < 10; a = a + 1)
        floatArr[a] = 0.0;	
      c = false;
      return;
    }"""
    val expected = """Continue Not In Loop"""
    assert(checkCkr(input,expected,441))
  }
  test("No Entry Point 134") {
    val input = """
    int gb_int;
    float gb_float;
    boolean gb_bool; // Undeclared global variable

    boolean init() {
      gb_int = 42;
      gb_float = 42.0;
      gb_bool = true; // Undeclared global variable

      return true;
    }

    int func_int(int param_int, float param_float, boolean param_bool) {
      int lc_int;
      float lc_float;
      boolean lc_bool;

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true;

      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float;
      }

      return lc_int;
    }

    float func_float(int param_int, float param_float, boolean param_bool) {
      int lc_int;
      float lc_float;
      boolean lc_bool;

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true;

      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float;
      }

      return lc_float;
    }

    int main() {
      int main_int;
      float main_float;
      boolean main_bool;

      if(!init()) return 1;

      main_int = 0;
      main_float = 0.0;
      main_bool = false;

      if (gb_bool && main_bool) {
        main_int = gb_int;
        main_float = gb_float;
      }
      else {
        int arg_int;
        float arg_float;
        boolean arg_bool;

        main_int = func_int(arg_int, arg_float, arg_bool);
        main_float = func_float(arg_int, arg_float, arg_bool);
      }

      return 0;
    }
    """
    val expected = """No Entry Point"""
    assert(checkCkr(input,expected,442))
  }
  test("Undeclared Identifier 135") {
    val input = """
    int gb_int;
    float gb_float;
    boolean gb_bool;

    boolean init() {
      gb_int = 42;
      gb_float = 42.0;
      gb_bool = true;

      return true;
    }

    int func_int(int param_int, float param_float, boolean param_bool) {
      int lc_int;
      float lc_float;
      boolean lc_bool;

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true;

      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float;
      }

      return lc_int;
    }

    float func_float(int param_int, float param_float, boolean param_bool) {
      int lc_int;
      float lc_float;
      // boolean lc_bool; // Undeclared local variable

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true; // Undeclared local variable

      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float;
      }

      return lc_float;
    }

    void main() {
      int main_int;
      float main_float;
      boolean main_bool;

      if(!init()) return 1;

      main_int = 0;
      main_float = 0.0;
      main_bool = false;

      if (gb_bool && main_bool) {
        main_int = gb_int;
        main_float = gb_float;
      }
      else {
        int arg_int;
        float arg_float;
        boolean arg_bool;

        main_int = func_int(arg_int, arg_float, arg_bool);
        main_float = func_float(arg_int, arg_float, arg_bool);
      }

      return;
    }
    """
    val expected = """Undeclared Identifier: lc_bool"""
    assert(checkCkr(input,expected,443))
  }
  test("Undeclared Identifier 136") {
    val input = """
    int gb_int;
    float gb_float;
    boolean gb_bool;

    boolean init() {
      gb_int = 42;
      gb_float = 42.0;
      gb_bool = true;

      return true;
    }

    int func_int(int param_int, float param_float, boolean param_bool) {
      int lc_int;
      float lc_float;
      boolean lc_bool;

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true;
      
      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float;
      }

      return lc_int;
    }

    float func_float(int param_int, /*float param_float, */ boolean param_bool) {
      int lc_int;
      float lc_float;
      boolean lc_bool;

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true;

      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float; // Undeclared parameter
      }

      return lc_float;
    }

    void main() {
      int main_int;
      float main_float;
      boolean main_bool;

      if(!init()) return 1;

      main_int = 0;
      main_float = 0.0;
      main_bool = false;

      if (gb_bool && main_bool) {
        main_int = gb_int;
        main_float = gb_float;
      }
      else {
        int arg_int;
        float arg_float;
        boolean arg_bool;

        main_int = func_int(arg_int, arg_float, arg_bool);
        main_float = func_float(arg_int, arg_float, arg_bool);
      }

      return;
    }
    """
    val expected = """Undeclared Identifier: param_float"""
    assert(checkCkr(input,expected,444))
  }
  test("Undeclared Identifier 137") {
    val input = """
    int gb_int;
    float gb_float;
    boolean gb_bool;

    boolean init() {
      gb_int = 42;
      gb_float = 42.0;
      gb_bool = true;

      return true;
    }

    int func_int(int param_int, float param_float, boolean param_bool) {
      int lc_int;
      float lc_float;
      boolean lc_bool;

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true;
      
      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float;
      }

      return lc_int;
    }

    float func_float(int param_int, float param_float, boolean param_bool) {
      int lc_int;
      float lc_float;
      boolean lc_bool;

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true;

      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float;
      }

      return lc_float;
    }

    void main() {
      int main_int;
      float main_float;
      boolean main_bool;

      if(!init()) return;

      main_int = 0;
      main_float = 0.0;
      main_bool = false;

      if (gb_bool && main_bool) {
        main_int = gb_int;
        main_float = gb_float;
      }
      else {
        int arg_int;
        float arg_float;
        // boolean arg_bool; // Undeclared argument

        main_int = func_int(arg_int, arg_float, main_bool);
        main_float = func_float(arg_int, arg_float, /*Undeclared argument*/ arg_bool);
      }

      return;
    }
    """
    val expected = """Undeclared Identifier: arg_bool"""
    assert(checkCkr(input,expected,445))
  }
  test("Undeclared Identifier 138") {
    val input = """
    int gb_int;
    float gb_float;
    boolean gb_bool;

    boolean init() {
      gb_int = 42;
      gb_float = 42.0;
      gb_bool = true;

      return true;
    }

    int func_int(int param_int, float param_float, boolean param_bool) {
      int lc_int;
      float lc_float;
      boolean lc_bool;

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true;
      
      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float;
      }

      return lc_int;
    }

    float func_float(int param_int, float param_float, boolean param_bool) {
      int lc_int;
      float lc_float;
      boolean lc_bool;

      lc_int = 1;
      lc_float = 1.0;
      lc_bool = true;

      if (gb_bool && lc_bool) {
        lc_int = gb_int;
        lc_float = gb_float;
      }
      else {
        lc_int = param_int;
        lc_float = param_float;
      }

      return returnValue; // Undeclared return value
    }

    void main() {
      int main_int;
      float main_float;
      boolean main_bool;

      if(!init()) return 1;

      main_int = 0;
      main_float = 0.0;
      main_bool = false;

      if (gb_bool && main_bool) {
        main_int = gb_int;
        main_float = gb_float;
      }
      else {
        int arg_int;
        float arg_float;
        boolean arg_bool;

        main_int = func_int(arg_int, arg_float, arg_bool);
        main_float = func_float(arg_int, arg_float, arg_bool);
      }

      return;
    }
    """
    val expected = """Undeclared Identifier: returnValue"""
    assert(checkCkr(input,expected,446))
  }
  test("Redeclared Parameter 139") {
    val input = """
    int foo(int a, int a){
      return 1;
    }
    void main(){
      return;
    }"""
    val expected = """Redeclared Parameter: a"""
    assert(checkCkr(input,expected,447))
  }
  test("Redeclared Variable 140") {
    val input = """
    int foo(int a, int b){
      int a;
      return 1;
    }
    void main(){
      return;
    }"""
    val expected = """Redeclared Variable: a"""
    assert(checkCkr(input,expected,448))
  }
  test("Redeclared Variable 141") {
    val input = """
    int foo(int a){
      {
      int a;
      }
      return 1;
    }
    void main(){
      int b;
      int b;
      return;
    }"""
    val expected = """Redeclared Variable: b"""
    assert(checkCkr(input,expected,449))
  }
  test("Unreachable Function 142") {
    val input = """
    boolean getBool(){
      return true;
    }
    void main(){
      return ;
    }"""
    val expected = """Unreachable Function: getBool"""
    assert(checkCkr(input,expected,450))
  }
  test("Type Mismatch In Expression 143") {
    val input = """
    int foo(int a, int b, boolean c, float d){
      return 1;
    }

    void main(){
      int x;
      int a;
      int b;
      int c;
      float d;
      x = foo(a, b, c, d);
      return;
    }"""
    val expected = """Type Mismatch In Expression: CallExpr(Id("foo"),List(Id("a"),Id("b"),Id("c"),Id("d")))"""
    assert(checkCkr(input,expected,451))
  }
  test("Type Mismatch In Statement 144") {
    val input = """
    void main (){
      int a;
      if (a = 1)
        return 1;
      else
        return 0;
      return;
    }"""
    val expected = """Type Mismatch In Statement: If(BinaryOp("=",Id("a"),IntLiteral(1)),Return(Some(IntLiteral(1))),Some(Return(Some(IntLiteral(0)))))"""
    assert(checkCkr(input,expected,452))
  }
  test("Type Mismatch In Statement 145") {
    val input = """
    int x;
    void main (){
      int i, y;
      for (i = 1; y = 5; i = i+1){
        x = x + 1;
      }	
      return;
    }"""
    val expected = """Type Mismatch In Statement: For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("=",Id("y"),IntLiteral(5)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(BinaryOp("=",Id("x"),BinaryOp("+",Id("x"),IntLiteral(1))))))"""
    assert(checkCkr(input,expected,453))
  }
  test("Type Mismatch In Statement 146") {
    val input = """
    void main (){
      int max;
      int i, y;
      for (i =0; i < 10; i = i +1){
        max = max + 1;
      }
      do max -1;
      while (max == y);
      return ;
    }
    void wrong (){
      int a;
      int b;
      b = a + 10;
      a = b + 100;
      return 1;
    }"""
    val expected = """Type Mismatch In Statement: Return(Some(IntLiteral(1)))"""
    assert(checkCkr(input,expected,454))
  }
  test("Type Mismatch In Statement 147") {
    val input = """
    int a;
    int b;
    void main (){
      int max;
      int count;
      int i;
      max = 5;
      for (i = 0; i < 10 ; i = i + 1){
        if (i < max) count= count +1;
        else count = count;
      }
      return;
    }
    int this_is_so_wrong (){
      a = 0;
      b = a +10;	
      return false;
    }"""
    val expected = """Type Mismatch In Statement: Return(Some(BooleanLiteral(false)))"""
    assert(checkCkr(input,expected,455))
  }
  test("Function Not Return 148") {
    val input = """
    int x;
    int y;
    int compare ( int a){
      int i;
      x = 10;
      y = 9;
      for (i = 0; i < a ; i= i + 1){
        a = a + 1;
      }
      if ( a == x)
      return y;
    }
    int result () {
      int z;
      z = compare (10);
      if (z = y)
      return 1;
      else return 0;
    }"""
    val expected = """Function Not Return: compare"""
    assert(checkCkr(input,expected,456))
  }
  test("Type Mismatch In Expression 149") {
    val input = """
    int x;
    void main (){
      int i;
      for (i = true; i < 5; i = i+1){
        x = x + 1;
      }	
      return ;
    }"""
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("i"),BooleanLiteral(true))"""
    assert(checkCkr(input,expected,457))
  }
  test("Redeclared Variable 150") {
    val input = """
    void main()
    {
        int e,c,f;
        boolean c;
        c = e;
        return ;
    }"""
    val expected = """Redeclared Variable: c"""
    assert(checkCkr(input,expected,458))
  }
  test("Redeclared Variable 151") {
    val input = """
    int i;
    float i;

    void main() 
    {
        f();
        return ;
    }"""
    val expected = """Redeclared Variable: i"""
    assert(checkCkr(input,expected,459))
  }
  test("Redeclared Variable 152") {
    val input = """
    int i;
    int Inc(int input) {
      return input + 1;
    }
    void main() {
        int main;
        main = Inc(i);
        putIntLn(i);
        {
          int i,foo;
          int main;
          int foo;
          main = foo = i = 100;
          putIntLn(i);		 
          putIntLn(Inc(main));
        }
        putIntLn(main);
    }"""
    val expected = """Redeclared Variable: foo"""
    assert(checkCkr(input,expected,460))
  }
  test("Redeclared Variable 153") {
    val input = """
    int abs;

    int Abs(int input) {
        if (input < 0) input = input * 1;
        return input;
    }

    void main() {
        int abs;
        abs = 0;
        if (abs == 0) {
            int abs;
            boolean a;
            abs = Abs(abs);
        }
        if (abs != 0) {
            int abs;
            boolean abs;
            abs = Abs(abs);
        }    
        return;
    }"""
    val expected = """Redeclared Variable: abs"""
    assert(checkCkr(input,expected,461))
  }
  test("Redeclared Variable 154") {
    val input = """
    int out;

    void main() {
        int in;
        {
        float in;
        {
          boolean in;
          int out[3];
        }
        }
        {
        float in;
        {
          boolean out;
          int out[3];
        }
        }    
        return;
    }"""
    val expected = """Redeclared Variable: out"""
    assert(checkCkr(input,expected,462))
  }
  test("Type Mismatch In Statement 155") {
    val input = """
    void main() {
      int a;
      float check;

        if (check) 
          a = 1;
        else 
          a = 2;
    }
    """
    val expected = """Type Mismatch In Statement: If(Id("check"),BinaryOp("=",Id("a"),IntLiteral(1)),Some(BinaryOp("=",Id("a"),IntLiteral(2))))"""
    assert(checkCkr(input,expected,463))
  }
  test("Type Mismatch In Statement 156") {
    val input = """
    int sum(int a, int b, int c) {
      int tong;
      tong = a;
      tong = tong + b;
      tong = tong + c;
      return tong;
    }

    int sum_error(int a, int b, int c) {
      int tong;
      tong = a;
      tong = tong + b;
      tong = tong + c;
      return true;
    }

    void main() {
      int tong1;
      int tong2;
      tong1 = sum(1, 2, 3);
      tong2 = sum_error(1, 2, 3);
    }"""
    val expected = """Type Mismatch In Statement: Return(Some(BooleanLiteral(true)))"""
    assert(checkCkr(input,expected,464))
  }
  test("Undeclared Identifier 157") {
    val input = """
    int a, b, c[5];
    void main(){
      float d;
      int e;
      f = 1;	// this is error
      return;
    }"""
    val expected = """Undeclared Identifier: f"""
    assert(checkCkr(input,expected,465))
  }
  test("Undeclared Identifier 158") {
    val input = """
    int a;
    int b;

    void main(){
      /* declaration */
      float d;
      int e, f, g[7];

      // statement part
      if (e == 2)
        h = 1;	// this is error

      return;
    }"""
    val expected = """Undeclared Identifier: h"""
    assert(checkCkr(input,expected,466))
  }
  test("Undeclared Identifier 159") {
    val input = """
    float a;
    boolean b;
    int c[5];

    void main(){
      /* declaration */
      float d;

      // statement part
      int e;
      for(e = 1; e < 100; e = e + 1){
        a = 100;
        b = true;
        d = a;
        f = f + 2;	// this is error
      }
      return;
    }"""
    val expected = """Undeclared Identifier: f"""
    assert(checkCkr(input,expected,467))
  }
  test("Undeclared Identifier 160") {
    val input = """
    float n1, n2, n3;

    int sqrt(int num){
        return num;
    }

    void main()
    {
        int a, b, c, determinant, root1,root2, realPart, imaginaryPart;

        putStringLn("Enter coefficients a, b and c: ");
        getInt();

        determinant = b * b - 4 * a * c;

        // condition for real and different roots
        if (determinant > 0)
        {
        // sqrt() function returns square root
            root1 = (-b + sqrt(determinant)) / (2*a);
            root2 = (-b - sqrt(determinant)) / (2*a);

            putStringLn("root1 = %.2lf and root2 = %.2lf");
        }

        //condition for real and equal roots
        else if (determinant == 0)
        {
            root1 = root2 = -b / (2*a);

            putStringLn("root1 = root2 = %.2lf;");
        }

        // if roots are not real 
        else
        {
            real = -b / (2*a);  // this is error
            imaginaryPart = sqrt(-determinant) / (2*a);
            putStringLn("root1 = %.2lf+%.2lfi and root2 = %.2f-%.2fi");
        }

        return;
    }  """
    val expected = """Undeclared Identifier: real"""
    assert(checkCkr(input,expected,468))
  }
  test("Undeclared Function 161") {
    val input = """
    void main()
    {
      int a, b;
      swap(a,b);
      return;
    }
    """
    val expected = """Undeclared Function: swap"""
    assert(checkCkr(input,expected,469))
  }
  test("Undeclared Function 162") {
    val input = """
    void main()
    {
      int a, b, c;
      c = tong(a, b);
      return;
    }
    """
    val expected = """Undeclared Function: tong"""
    assert(checkCkr(input,expected,470))
  }
  test("Type Mismatch In Expression 163") {
    val input = """
    int tich(float a, float b) {
      int result;
      result = a * b;
      return result;
    }

    void main() {
      tich(5.5, 2.5);
    }"""
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("result"),BinaryOp("*",Id("a"),Id("b")))"""
    assert(checkCkr(input,expected,471))
  }
  test("Type Mismatch In Expression 164") {
    val input = """
    int tich(int a, int b) {
      int result;
      result = a * b;
      return result;
    }

    void main() {
      int abc;
      abc = tich(5.5, 2.5);
    }"""
    val expected = """Type Mismatch In Expression: CallExpr(Id("tich"),List(FloatLiteral(5.5f),FloatLiteral(2.5f)))"""
    assert(checkCkr(input,expected,472))
  }
  test("Type Mismatch In Expression 165") {
    val input = """
    int sum(int a, int b, int c) {
      int tong;
      int tmp;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sum2(float a, float b, float c) {
      float tong;
      int tmp;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sub(float a, float b, int c) {
      float hieu;
      float tmp;
      tmp = a - b;
      hieu = tmp - c;
      return hieu;
    }

    void main() {
      sum(5 + 1, 1 + 6, 2 + 4);
      sub(5 * 1, 1 * 4, 1 * 1);
      sum2(1, 2, 3);
    }"""
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("tmp"),BinaryOp("+",Id("a"),Id("b")))"""
    assert(checkCkr(input,expected,473))
  }
  test("Type Mismatch In Expression 166") {
    val input = """
    int sum(int a, int b, int c) {
      int tong;
      int tmp;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sum2(float a, float b, float c) {
      int arr[5];
      float tong;
      float tmp;
      arr[1] = 123;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sub(float a, float b, int c) {
      int arr[5];
      float hieu;
      float tmp;
      arr[1.5] = 123;
      tmp = a - b;
      hieu = tmp - c;
      return hieu;
    }

    void main() {
      sum(5 + 1, 1 + 6, 2 + 4);
      sub(5 * 1, 1 * 4, 1 * 1);
      sum2(1, 2, 3);
    }"""
    val expected = """Type Mismatch In Expression: ArrayCell(Id("arr"),FloatLiteral(1.5f))"""
    assert(checkCkr(input,expected,474))
  }
  test("Type Mismatch In Expression 167") {
    val input = """
    int sum(int a, int b, int c) {
      int tong;
      int tmp;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sum2(float a, float b, float c) {
      int arr[5];
      float tong;
      float tmp;
      arr[1] = 123;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sub(float a, float b, int c) {
      float hieu;
      float tmp;
      tmp = a - b;
      hieu = tmp - c;
      return hieu;
    }

    void main() {
      boolean arrb[10];
      sum(5 + 1, 1 + 6, 2 + 4);
      sub(5 * 1, 1 * 4, 1 * 1);
      sum2(1, 2, 3);
      arrb[1] = 12;
    }"""
    val expected = """Type Mismatch In Expression: BinaryOp("=",ArrayCell(Id("arrb"),IntLiteral(1)),IntLiteral(12))"""
    assert(checkCkr(input,expected,475))
  }
  test("Redeclared Parameter 168") {
    val input = """
    void main() {
        int a;
        int b;
        int c;
        int d;
        
        return ; 
    }

    int test(int dup, int dup){
        int e;
        int f;
        int g;
        int h;
    }"""
    val expected = """Redeclared Parameter: dup"""
    assert(checkCkr(input,expected,476))
  }
  test("Function Not Return 169") {
    val input = """
    int test() {
      int a;
      int b;
      int c;
      int d;
      if (a>2.1)
          if (b<-1)
              if (c == 2)
                  d = 4;
    }
    void main(){}
    int test1(int a, int b, int c, int d, int bug, int f, int bug){
      if (a>2.1)
            if (b<-1)
                if (c == 2)
                    d = 4;
    }"""
    val expected = """Function Not Return: test"""
    assert(checkCkr(input,expected,477))
  }
  test("Redeclared Parameter 170") {
    val input = """
    int sum(int a, int b) {
      int tong;
      float src;
      tong = a + b;
      return tong;
    }

    float sub(float a, float b, float thisbug, float thisbug) {
      float hieu;
      float src;
      hieu = a - b;
      return hieu;
    }

    void main() {
      sum(5 + 1, 1 + 6);
      sub(5 * 1, 1 * 4);
    }"""
    val expected = """Redeclared Parameter: thisbug"""
    assert(checkCkr(input,expected,478))
  }
  test("Redeclared Variable 171") {
    val input = """
    void main()
    {
        int e,c,f;
        boolean c;
        c = e;
        return;
    }"""
    val expected = """Redeclared Variable: c"""
    assert(checkCkr(input,expected,479))
  }
  test("Redeclared Variable 172") {
    val input = """
    int i;
    float i;

    void main() 
    {
        f();
        return 1;
    }"""
    val expected = """Redeclared Variable: i"""
    assert(checkCkr(input,expected,480))
  }
  test("Redeclared Variable 173") {
    val input = """
    int i;
    int Inc(int input) {
      return input + 1;
    }
    void main() {
        int main;
        main = Inc(i);
        putIntLn(i);
        {
        int i,foo;
        int main;
        int foo;
        main = foo = i = 100;
        putIntLn(i);		 
        putIntLn(Inc(main));
        }
        putIntLn(main);
    }"""
    val expected = """Redeclared Variable: foo"""
    assert(checkCkr(input,expected,481))
  }
  test("No Entry Point 174") {
    val input = """
    int abs;
    int Abs(int input) {
        if (input < 0) input = input * 1;
        return input;
    }

    int main() {
        int abs;
        abs = 0;
        if (abs == 0) {
            int abs;
            boolean a;
            abs = Abs(abs);
        }
        if (abs != 0) {
            int abs;
            boolean a;
            abs = Abs(abs);
        }    
        return 0;
    }"""
    val expected = """No Entry Point"""
    assert(checkCkr(input,expected,482))
  }
  test("Redeclared Variable 175") {
    val input = """
    int out;

    void main() {
        int in;
        {
        float in;
        {
          boolean in;
          int out[3];
        }
        }
        {
        float in;
        {
          boolean out;
          int out[3];
        }
        }    
        return;
    }"""
    val expected = """Redeclared Variable: out"""
    assert(checkCkr(input,expected,483))
  }
  test("Type Mismatch In Statement 176") {
    val input = """
    void main() {
      int a;
      float b;
      a = a + 1;

        if (b) 
          a = 1;
        else 
          a = 2;
    }
    """
    val expected = """Type Mismatch In Statement: If(Id("b"),BinaryOp("=",Id("a"),IntLiteral(1)),Some(BinaryOp("=",Id("a"),IntLiteral(2))))"""
    assert(checkCkr(input,expected,484))
  }
  test("Undeclared Identifier 177") {
    val input = """
    int sum(int a, int b, int c) {
      int tong;
      tong = a;
      tong = tong + b;
      tong = tong + c;
      a = a + b;
      return tong;
    }

    int sum_error(int a, int b) {
      int tong;
      tong = a;
      tong = tong + b;
      tong = tong + c;
      return true;
    }"""
    val expected = """Undeclared Identifier: c"""
    assert(checkCkr(input,expected,485))
  }
  test("Undeclared Identifier 178") {
    val input = """
    int a, b, c[5];
    void main(){
      float k;
      int e;
      k = e + 2;
      f = 1;	// this is error
      return;
    }"""
    val expected = """Undeclared Identifier: f"""
    assert(checkCkr(input,expected,486))
  }
  test("Undeclared Identifier 179") {
    val input = """
    int a;
    int b;

    void main(){
      /* declaration */
      float p;
      int e, f, g[7];

      // statement part
      if (e == 2)
        h = 1;	// this is error

      return;
    }"""
    val expected = """Undeclared Identifier: h"""
    assert(checkCkr(input,expected,487))
  }
  test("Undeclared Identifier 180") {
    val input = """
    float a;
    boolean b;
    int c[5];
    float test(){
      int f;
      return f;
    }

    void main(){
      /* declaration */
      float d;

      // statement part
      int e;
      for(e = 1; e < 100; e = e + 1){
        a = 100;
        b = true;
        d = a;
        f = f + 2;	// this is error
      }
      return;
    }"""
    val expected = """Undeclared Identifier: f"""
    assert(checkCkr(input,expected,488))
  }
  test("Undeclared Function 181") {
    val input = """
    void main()
    {
        int a, b, c, determinant, root1,root2, realPart, imaginaryPart;

        putStringLn("Enter coefficients a, b and c: ");
        getInt();

        determinant = b * b - 4 * a * c;

        // condition for real and different roots
        if (determinant > 0)
        {
        // sqrt() function returns square root
            root1 = (-b + sqrt(determinant)) / (2*a);
            root2 = (-b - sqrt(determinant)) / (2*a);

            putStringLn("root1 = %.2lf and root2 = %.2lf");
        }

        //condition for real and equal roots
        else if (determinant == 0)
        {
            root1 = root2 = -b / (2*a);

            putStringLn("root1 = root2 = %.2lf;");
        }

        // if roots are not real 
        else
        {
            real = -b / (2*a);  // this is error
            imaginaryPart = sqrt(-determinant) / (2*a);
            putStringLn("root1 = %.2lf+%.2lfi and root2 = %.2f-%.2fi");
        }

        return ;
    }  """
    val expected = """Undeclared Function: sqrt"""
    assert(checkCkr(input,expected,489))
  }
  test("Undeclared Function 182") {
    val input = """
    void main()
    {
      int a, b;
      divide(a,b);
      return;
    }
    """
    val expected = """Undeclared Function: divide"""
    assert(checkCkr(input,expected,490))
  }
  test("Type Mismatch In Expression 183") {
    val input = """
    float tong(float a, float b){
      return a+b;
    }
    void main()
    {
      float a, b;
      int c;
      c = tong(a, b);
      return;
    }
    """
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("c"),CallExpr(Id("tong"),List(Id("a"),Id("b"))))"""
    assert(checkCkr(input,expected,491))
  }
  test("Type Mismatch In Expression 184") {
    val input = """
    int[] test1(float a, int b[], int c){ 
      return b;
      }
    void main() {
      int main;
      int a;
      int b[4];
      int c;
      b[3] = test1(a, b, c)[4];
      c = b; 
      return; 
    }
    """
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("c"),Id("b"))"""
    assert(checkCkr(input,expected,492))
  }
  test("Type Mismatch In Expression 185") {
    val input = """
    int tich(int a, int b) {
      int result;
      result = a * b;
      return result;
    }
    void test()
    {
      return;
    }

    void main() {
      int abc;
      abc = tich(2, 2);
    }"""
    val expected = """Unreachable Function: test"""
    assert(checkCkr(input,expected,493))
  }
  test("Type Mismatch In Expression 186") {
    val input = """
    int sum(int a, int b, int c) {
      int tong;
      int tmp;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sum2(float a, float b, float c) {
      float tong;
      int tmp;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sub(float a, float b) {
      float hieu;
      float tmp;
      tmp = a - b;
      hieu = tmp - c;
      return hieu;
    }

    void main() {
      sum(5 + 1, 1 + 6, 2 + 4);
      sub(5 * 1, 1 * 4, 1 * 1);
      sum2(1, 2, 3);
    }"""
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("tmp"),BinaryOp("+",Id("a"),Id("b")))"""
    assert(checkCkr(input,expected,494))
  }
  test("Type Mismatch In Expression 187") {
    val input = """
    float sum2(float a, float b, float c) {
      int arr[5];
      float tong;
      float tmp;
      arr[1] = 123;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sub(float a, float b, int c) {
      int arr[5];
      float hieu;
      float tmp;
      arr[0.5] = 123;
      tmp = a - b;
      hieu = tmp - c;
      return hieu;
    }"""
    val expected = """Type Mismatch In Expression: ArrayCell(Id("arr"),FloatLiteral(0.5f))"""
    assert(checkCkr(input,expected,495))
  }
  test("Type Mismatch In Expression 188") {
    val input = """
    float sum2(float a, float b, float c) {
      int arr[5];
      float tong;
      float tmp;
      arr[1] = 123;
      tmp = a + b;
      tong = tmp + c;
      return tong;
    }

    float sub(float a, float b, int c) {
      float hieu;
      float tmp;
      tmp = a - b;
      hieu = tmp - c;
      return hieu;
    }

    void main() {
      boolean arrb[10];
      sub(5 * 1, 1 * 4, 1 * 1);
      sum2(1, 2, 3);
      arrb[1] = 12;
    }"""
    val expected = """Type Mismatch In Expression: BinaryOp("=",ArrayCell(Id("arrb"),IntLiteral(1)),IntLiteral(12))"""
    assert(checkCkr(input,expected,496))
  }
  test("Type Mismatch In Expression 189") {
    val input = """
    int[] test1(float a, int b[], int c){ 
      return b;
      }
    void main() {
      int main;
      int a;
      int b[4];
      int c;
      b = test1(a, b, c)[4];
      return; 
    }"""
    val expected = """Type Mismatch In Expression: BinaryOp("=",Id("b"),ArrayCell(CallExpr(Id("test1"),List(Id("a"),Id("b"),Id("c"))),IntLiteral(4)))"""
    assert(checkCkr(input,expected,497))
  }
  test("Unreachable Function 190") {
    val input = """
    int[] test(int k[]) {
      int a[4];
    return a;
    }

    int[] test2() {
      int a[4];
    return test(a);
    }

    int[] test3() {
      int a[4];
    return a;
    }

    void main(){
      int a[4];
      a[3] = test2()[3];
    }
    """
    val expected = """Unreachable Function: test3"""
    assert(checkCkr(input,expected,498))
  }
  test("Unreachable Function 191") {
    val input = """
    int sum(int a, int b) {
      int tong;
      float src;
      tong = a + b;
      return tong;
    }
    void test()
    {
      return;
    }
    float sub(float a, float b) {
      float hieu;
      float src;
      hieu = a - b;
      return hieu;
    }

    int test1(){
      return 1;
    }
    int test2(){
      return 1;
    }
    int test3(){
      return 1;
    }
    int test4(){
      return 1;
    }

    int test5(){
      return 1;
    }
    int test6(){
      return 1;
    }

    void main() {
      int a, b;
      if (a == b)
      {
        test1();
      }
      else
        test2();
      for(a = 1; a < 2; a=a+1)
      {
        test3();
        for(a = 1; a < 2; a=a+1)
          test4();
      }
      do
      test5();
      while(true);
      do{
        test6();
      }
      while(false);
      sum(5 + 1, 1 + 6);
      sub(5 * 1, 1 * 4);
    }"""
    val expected = """Unreachable Function: test"""
    assert(checkCkr(input,expected,499))
  }
  test("Unreachable Statement 192") {
    val input = """
    int compare(int a, int b){
      int result;
      boolean c, d;
      if (c = d) result = a;
      else result = b;
      return result;
    }
    int program(){
      int x,y;
      y = 50;
      for (x = 1; x <= y; x = x + 1){
        if (compare(x, y) >= x)
        {
          break;
          break;
        }
        else continue;
      }
    } """
    val expected = """Unreachable Statement: Break"""
    assert(checkCkr(input,expected,500))
  }
}