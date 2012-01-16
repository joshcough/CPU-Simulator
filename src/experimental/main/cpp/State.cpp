#include <iostream>
using namespace std;


class State {
  bool on;
  public:
    State(bool o){ on = o; }
    State operator && (State s){ return State(on && s.on); }
    State change(){ return State(!on); }
    void print(){ cout << "State:" << on << "\n"; }
} On(true), Off(false);


int main () {
  State a (true);
  State b (false);
  State c = On;
  a.print();
  b.print();
  c.print();
  State d = (a && b);
  d.print();
  (a&&c).print();
  (On&&On).print();
  (On&&Off).print();
  (Off&&On).print();
  (Off&&Off).print();
  return 0;
}
