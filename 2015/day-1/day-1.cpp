#include <iostream>
#include <fstream>
#include <string>
using namespace std;

string getFileContents(void);

int main () {
  string line = getFileContents();
  int index = 0;
  int floorLevel = 0;
  bool isFirstBase = true;
  int firstBase = -99;

  while (line[index] == '(' || line[index] == ')'){
    if (line[index] == '(')
      floorLevel += 1;
    else floorLevel -= 1;
    index += 1;

    if (floorLevel == -1 && isFirstBase){
      firstBase = index;
      isFirstBase = false;
    }
  }
  
  cout << floorLevel << endl;
  cout << firstBase << endl;
  
  return 0;
}

string getFileContents(){
  string line;
  ifstream file("input.txt");

  if (file.is_open()){
    getline(file, line);
    file.close();
  }

  return line;
}