import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/28/17.
  */
class LexerSuite extends FunSuite with TestLexer {

test("Blank characters 1") {
val input ="         "
val expected ="<EOF>"
assert(checkLex(input,expected,1))
}
test("Block comment") {
val input ="/* abc */"
val expected ="<EOF>"
assert(checkLex(input,expected,2))
}
test("Block comment 2") {
val input ="/*/* abcd */"
val expected ="<EOF>"
assert(checkLex(input,expected,3))
}
test("Block comment 3") {
val input ="/* // \\n \\t */"
val expected ="<EOF>"
assert(checkLex(input,expected,4))
}
test("Line comment") {
val input ="// comment here"
val expected ="<EOF>"
assert(checkLex(input,expected,5))
}
test("Line comment 2") {
val input ="///////*** abc */"
val expected ="<EOF>"
assert(checkLex(input,expected,6))
}
test("Block comment4") {
val input ="/*/* abc  */ */"
val expected ="*,/,<EOF>"
assert(checkLex(input,expected,7))
}
test("Block comment5") {
val input ="/ * */"
val expected ="/,*,*,/,<EOF>"
assert(checkLex(input,expected,8))
}
test("Identifiers 1") {
val input ="_abc123"
val expected ="_abc123,<EOF>"
assert(checkLex(input,expected,9))
}
test("Identifiers 2") {
val input ="a_b_c"
val expected ="a_b_c,<EOF>"
assert(checkLex(input,expected,10))
}
test("Identifiers 3") {
val input ="123_abc"
val expected ="123,_abc,<EOF>"
assert(checkLex(input,expected,11))
}
test("Identifiers 4") {
val input ="aa12aa_123_aa"
val expected ="aa12aa_123_aa,<EOF>"
assert(checkLex(input,expected,12))
}
test("Identifiers 5") {
val input ="AasdB"
val expected ="AasdB,<EOF>"
assert(checkLex(input,expected,13))
}
test("Keywords 1") {
val input ="boolean"
val expected ="boolean,<EOF>"
assert(checkLex(input,expected,14))
}
test("Keywords 2") {
val input ="break"
val expected ="break,<EOF>"
assert(checkLex(input,expected,15))
}
test("Keywords 3") {
val input ="_break"
val expected ="_break,<EOF>"
assert(checkLex(input,expected,16))
}
test("Keywords 4") {
val input ="break_"
val expected ="break_,<EOF>"
assert(checkLex(input,expected,17))
}
test("Keywords 5") {
val input ="continueelseforfloatifintreturnvoiddowhiletruefalsestring"
val expected ="continueelseforfloatifintreturnvoiddowhiletruefalsestring,<EOF>"
assert(checkLex(input,expected,18))
}
test("Keywords 6") {
val input ="continue else for float if int return void do while true false string"
val expected ="continue,else,for,float,if,int,return,void,do,while,true,false,string,<EOF>"
assert(checkLex(input,expected,19))
}
test("Operators 1") {
val input ="+ - * / ! % || && != == < > <= >= ="
val expected ="+,-,*,/,!,%,||,&&,!=,==,<,>,<=,>=,=,<EOF>"
assert(checkLex(input,expected,20))
}
test("Separators 1") {
val input ="[ ] ( ) { } ; ,"
val expected ="[,],(,),{,},;,,,<EOF>"
assert(checkLex(input,expected,21))
}
test("Int literals 1") {
val input ="00"
val expected ="00,<EOF>"
assert(checkLex(input,expected,22))
}
test("Int literals 2") {
val input ="0 01"
val expected ="0,01,<EOF>"
assert(checkLex(input,expected,23))
}
test("Float literals 1") {
val input ="0."
val expected ="0.,<EOF>"
assert(checkLex(input,expected,24))
}
test("Float literals 2") {
val input ="0.012"
val expected ="0.012,<EOF>"
assert(checkLex(input,expected,25))
}
test("Float literals 3") {
val input ="0.012E"
val expected ="0.012,E,<EOF>"
assert(checkLex(input,expected,26))
}
test("Float literals 4") {
val input ="0.012E-"
val expected ="0.012,E,-,<EOF>"
assert(checkLex(input,expected,27))
}
test("Float literals 5") {
val input ="-0.012E-0"
val expected ="-0.012E-0,<EOF>"
assert(checkLex(input,expected,28))
}
test("Float literals 6") {
val input =".0"
val expected =".0,<EOF>"
assert(checkLex(input,expected,29))
}
test("Float literals 7") {
val input =".012"
val expected =".012,<EOF>"
assert(checkLex(input,expected,30))
}
test("Float literals 8") {
val input =".012E-234.4"
val expected =".012E-234,.4,<EOF>"
assert(checkLex(input,expected,31))
}
test("Float literals 9") {
val input =".012E-"
val expected =".012,E,-,<EOF>"
assert(checkLex(input,expected,32))
}
test("Float literals 10") {
val input ="-.012E-0"
val expected ="-.012E-0,<EOF>"
assert(checkLex(input,expected,33))
}
test("Float literals 11") {
val input ="12E-1"
val expected ="12E-1,<EOF>"
assert(checkLex(input,expected,34))
}
test("Float literals 12") {
val input ="12e-1"
val expected ="12e-1,<EOF>"
assert(checkLex(input,expected,35))
}
test("String literals 1") {
val input =""""acb""""
val expected ="""acb,<EOF>"""
assert(checkLex(input,expected,36))
}
test("String literals 2") {
val input =""""sdadg` !@#$%^&*()\"""""
val expected ="""sdadg` !@#$%^&*()\",<EOF>"""
assert(checkLex(input,expected,37))
}
test("String literals 3") {
val input =""""\"\'""""
val expected ="""\"\',<EOF>"""
assert(checkLex(input,expected,38))
}
test("String literals 4") {
val input =""""\n\t\\\b\r\f""""
val expected ="""\n\t\\\b\r\f,<EOF>"""
assert(checkLex(input,expected,39))
}
test("Error tokens 1") {
val input ="`"
val expected ="ErrorToken `"
assert(checkLex(input,expected,40))
}
test("Error tokens 2") {
val input ="@"
val expected ="ErrorToken @"
assert(checkLex(input,expected,41))
}
test("Error tokens 3") {
val input ="|"
val expected ="ErrorToken |"
assert(checkLex(input,expected,42))
}
test("Error tokens 4") {
val input ="?"
val expected ="ErrorToken ?"
assert(checkLex(input,expected,43))
}
test("Error tokens 5") {
val input ="^"
val expected ="ErrorToken ^"
assert(checkLex(input,expected,44))
}
test("Error tokens 6") {
val input ="#"
val expected ="ErrorToken #"
assert(checkLex(input,expected,45))
}
test("Error tokens 7") {
val input ="$"
val expected ="ErrorToken $"
assert(checkLex(input,expected,46))
}
test("Error tokens 8") {
val input ="~"
val expected ="ErrorToken ~"
assert(checkLex(input,expected,47))
}
test("Unclosed String 1") {
val input ="""""""
val expected ="""Unclosed string: """"
assert(checkLex(input,expected,48))
}
test("Unclosed String 2") {
val input =""""\""""
val expected ="""Unclosed string: "\""""
assert(checkLex(input,expected,49))
}
test("Unclosed String 3") {
val input =""""\""""""
val expected ="""\",Unclosed string: """"
assert(checkLex(input,expected,50))
}
test("Unclosed String 4") {
val input =""""@$%^&*"""
val expected ="""Unclosed string: "@$%^&*"""
assert(checkLex(input,expected,51))
}
test("Unclosed String 5") {
val input =""""'"""""
val expected ="""',Unclosed string: """"
assert(checkLex(input,expected,52))
}
test("Unclosed String 6") {
val input =""""ABC \\\"\""""
val expected ="""Unclosed string: "ABC \\\"\""""
assert(checkLex(input,expected,53))
}
test("Illegal escape in string1") {
val input =""""\a""""
val expected ="""Illegal escape in string: "\a""""
assert(checkLex(input,expected,54))
}
test("Illegal escape in string 2") {
val input =""""\\\q""""
val expected ="""Illegal escape in string: "\\\q""""
assert(checkLex(input,expected,55))
}
test("Illegal escape in string 3") {
val input =""""== a + b \k""""
val expected ="""Illegal escape in string: "== a + b \k""""
assert(checkLex(input,expected,56))
}
test("Illegal escape in string 4") {
val input =""""== a + b [] {} \" \k""""
val expected ="""Illegal escape in string: "== a + b [] {} \" \k""""
assert(checkLex(input,expected,57))
}
test("combine 1") {
val input ="float f = 1.0; "
val expected ="float,f,=,1.0,;,<EOF>"
assert(checkLex(input,expected,58))
}
test("combine 2") {
val input ="int i [5]; // CORRECT"
val expected ="int,i,[,5,],;,<EOF>"
assert(checkLex(input,expected,59))
}
test("combine 3") {
val input ="int i,j,k[5];"
val expected ="int,i,,,j,,,k,[,5,],;,<EOF>"
assert(checkLex(input,expected,60))
}
test("combine 4") {
val input ="int child_of_foo(float f ){} //ERROR"
val expected ="int,child_of_foo,(,float,f,),{,},<EOF>"
assert(checkLex(input,expected,61))
}
test("combine 5") {
val input ="1.2"
val expected ="1.2,<EOF>"
assert(checkLex(input,expected,62))
}
test("combine 6") {
val input ="boolean b; // a variable of type boolean"
val expected ="boolean,b,;,<EOF>"
assert(checkLex(input,expected,63))
}
test("combine 7") {
val input ="int i; // a variable of type int "
val expected ="int,i,;,<EOF>"
assert(checkLex(input,expected,64))
}
test("combine 8") {
val input ="float f; // a variable of type ﬂoat "
val expected ="float,f,;,<EOF>"
assert(checkLex(input,expected,65))
}
test("combine 9") {
val input ="boolean ba[5]; // a variable of type array on boolean "
val expected ="boolean,ba,[,5,],;,<EOF>"
assert(checkLex(input,expected,66))
}
test("combine 10") {
val input ="int ia[3]; // a variable of type array on int "
val expected ="int,ia,[,3,],;,<EOF>"
assert(checkLex(input,expected,67))
}
test("combine 11") {
val input ="float fa[100]; // a variable of type array on ﬂoat "
val expected ="float,fa,[,100,],;,<EOF>"
assert(checkLex(input,expected,68))
}
test("combine 12") {
val input ="int i=5; //no initialization => int i; "
val expected ="int,i,=,5,;,<EOF>"
assert(checkLex(input,expected,69))
}
test("combine 13") {
val input ="float f[]; //must have size => ﬂoat f[5]; "
val expected ="float,f,[,],;,<EOF>"
assert(checkLex(input,expected,70))
}
test("combine 14") {
val input ="boolean boo[2]={true,false}; //no initialization => boolean boo[2]; "
val expected ="boolean,boo,[,2,],=,{,true,,,false,},;,<EOF>"
assert(checkLex(input,expected,71))
}
test("combine 15") {
val input ="if (i>0) putInt( i );"
val expected ="if,(,i,>,0,),putInt,(,i,),;,<EOF>"
assert(checkLex(input,expected,72))
}
test("combine 16") {
val input ="foo(2)[3+x] = a[b[2]] +3; "
val expected ="foo,(,2,),[,3,+,x,],=,a,[,b,[,2,],],+,3,;,<EOF>"
assert(checkLex(input,expected,73))
}
test("combine 17") {
val input ="void f(int a[10]) { } "
val expected ="void,f,(,int,a,[,10,],),{,},<EOF>"
assert(checkLex(input,expected,74))
}
test("combine 18") {
val input ="foo(x); //CORRECT "
val expected ="foo,(,x,),;,<EOF>"
assert(checkLex(input,expected,75))
}
test("combine 19") {
val input ="int [] foo(int b[]) { "
val expected ="int,[,],foo,(,int,b,[,],),{,<EOF>"
assert(checkLex(input,expected,76))
}
test("combine 20") {
val input ="else return b; //CORRECT"
val expected ="else,return,b,;,<EOF>"
assert(checkLex(input,expected,77))
}
test("combine 21") {
val input ="main = f = i = 100; //abc "
val expected ="main,=,f,=,i,=,100,;,<EOF>"
assert(checkLex(input,expected,78))
}
test("combine 22") {
val input ="1."
val expected ="1.,<EOF>"
assert(checkLex(input,expected,79))
}
test("combine 23") {
val input =".1"
val expected =".1,<EOF>"
assert(checkLex(input,expected,80))
}
test("combine 24") {
val input ="1e2"
val expected ="1e2,<EOF>"
assert(checkLex(input,expected,81))
}
test("combine 25") {
val input ="1.2E-2"
val expected ="1.2E-2,<EOF>"
assert(checkLex(input,expected,82))
}
test("combine 26") {
val input ="1.2e-2"
val expected ="1.2e-2,<EOF>"
assert(checkLex(input,expected,83))
}
test("combine 27") {
val input =".1E2"
val expected =".1E2,<EOF>"
assert(checkLex(input,expected,84))
}
test("combine 28") {
val input ="9.0"
val expected ="9.0,<EOF>"
assert(checkLex(input,expected,85))
}
test("combine 29") {
val input ="12e8"
val expected ="12e8,<EOF>"
assert(checkLex(input,expected,86))
}
test("combine 30") {
val input ="0.33E-3"
val expected ="0.33E-3,<EOF>"
assert(checkLex(input,expected,87))
}
test("combine 31") {
val input ="128e-42 "
val expected ="128e-42,<EOF>"
assert(checkLex(input,expected,88))
}
test("Keywords 10") {
val input ="else"
val expected ="else,<EOF>"
assert(checkLex(input,expected,89))
}
test("Keywords 11") {
val input ="for"
val expected ="for,<EOF>"
assert(checkLex(input,expected,90))
}
test("Keywords 12") {
val input ="float"
val expected ="float,<EOF>"
assert(checkLex(input,expected,91))
}
test("Keywords 13") {
val input ="if"
val expected ="if,<EOF>"
assert(checkLex(input,expected,92))
}
test("Keywords 14") {
val input ="int"
val expected ="int,<EOF>"
assert(checkLex(input,expected,93))
}
test("Keywords 15") {
val input ="return"
val expected ="return,<EOF>"
assert(checkLex(input,expected,94))
}
test("Keywords 16") {
val input ="void"
val expected ="void,<EOF>"
assert(checkLex(input,expected,95))
}
test("Keywords 17") {
val input ="do"
val expected ="do,<EOF>"
assert(checkLex(input,expected,96))
}
test("Keywords 18") {
val input ="while"
val expected ="while,<EOF>"
assert(checkLex(input,expected,97))
}
test("Keywords 19") {
val input ="true"
val expected ="true,<EOF>"
assert(checkLex(input,expected,98))
}
test("Keywords 20") {
val input ="false"
val expected ="false,<EOF>"
assert(checkLex(input,expected,99))
}
test("Keywords 21") {
val input ="string"
val expected ="string,<EOF>"
assert(checkLex(input,expected,100))
}
}