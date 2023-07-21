#include <iostream>
#include <fstream>
#include <string>
using namespace std;

string getFileContents(void);

int main () {
  string line = getFileContents();  
  
  return 0;
}

string getFileContents(){
  string line;
  ifstream file("input.txt");

  if (file.is_open()){
    while(getline(file, line)){}
    file.close();
  }

  return line;
}