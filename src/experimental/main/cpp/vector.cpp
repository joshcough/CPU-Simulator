#include <iostream>
using namespace std;

class CList {
  public:
    int x,y;
    CList () {};
    CList (int,int);
    CList operator + (CList);
};

CList::CList (int a, int b) {
  x = a;
  y = b;
}

CList CList::operator+ (CList param) {
  CList temp;
  temp.x = x + param.x;
  temp.y = y + param.y;
  return (temp);
}

int main () {
  CList a (3,1);
  CList b (1,2);
  CList c;
  c = a + b;
  cout << c.x << "," << c.y;
  return 0;
}
