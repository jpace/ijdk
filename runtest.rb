#!/usr/bin/ruby -w
# -*- ruby -*-

require 'pathname'

class TestFile
  def initialize name
    pn = Pathname.new name
    puts "pn: #{pn}"
      
    name = Pathname.new(pn.to_s.sub Pathname.pwd.to_s, "")
    names = name.each_filename.to_a

    names[-1].sub! ".java", ""
    
    unless names[-1].start_with? "Test"
      names = names[0 .. -2] << ("Test" + names[-1])
    end
    
    testname = names[3 .. -1].join "."
    puts "testname: #{testname}"

    cmd = "gradle test --offline --tests #{testname}"
    puts "cmd: #{cmd}"
    IO.popen(cmd) do |io|
      io.each_line do |line|
        puts line
      end
    end
  end
end

ARGV.each do |arg|
  TestFile.new arg
end
