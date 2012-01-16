#include <Wire>

class WireTest : public CppUnit::TestCase {
  public: WireTest( std::string name ) : CppUnit::TestCase( name ) {}
  void runTest() {
    CPPUNIT_ASSERT( Wire (10, 1) == Wire (10, 1) );
    CPPUNIT_ASSERT( !(Wire (1, 1) == Wire (2, 2)) );
    }
};

