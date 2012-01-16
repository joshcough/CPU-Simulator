#include <iostream>
using namespace std;

class CWire {
  public:
    int x,y;
    CWire () {};
    CWire (int,int);
    CWire operator + (CWire);
};

CWire::CWire (int a, int b) {
  x = a;
  y = b;
}

CWire CWire::operator+ (CWire param) {
  CWire temp;
  temp.x = x + param.x;
  temp.y = y + param.y;
  return (temp);
}

int main () {
  CWire a (3,1);
  CWire b (1,2);
  CWire c;
  c = a + b;
  cout << c.x << "," << c.y;
  return 0;
}
