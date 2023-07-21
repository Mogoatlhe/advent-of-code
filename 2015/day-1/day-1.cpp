#include <iostream>
#include <fstream>
#include <string>
using namespace std;

string getFileContents(void);

int main () {
  string line = getFileContents();
  int index = 0;
  int floorLevel = 0;
  
  while (line[index] == '(' || line[index] == ')'){
    if (line[index] == '(')
      floorLevel += 1;
    else floorLevel -= 1;
    index += 1;
  }
  
  cout << floorLevel << endl;
  
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