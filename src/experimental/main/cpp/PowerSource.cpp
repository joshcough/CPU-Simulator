#include <State>

class PowerSource{
  public:
  virtual State state(void);
  virtual void handleStateChanged(PowerSource);
  virtual void notifyConnections(void);
  virtual PowerSource connect(PowerSource);
  virtual PowerSource loopBack(PowerSource);
  virtual PowerSource disconnectFrom (PowerSource);
  virtual PowerSource disconnectedFrom (PowerSource);
};