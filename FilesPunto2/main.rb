require 'benchmark'

def print_measures
 time = Benchmark.measure {
    file = File.open('C:\Users\user\Documents\Lab1ED\LabED1\FilesPunto2\finaldata.csv')

    puts file.readlines
    
    file.close
 }
 puts time.real #or save it to logs
end
print_measures()
