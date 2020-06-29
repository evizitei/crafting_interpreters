package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;
import com.craftinginterpreters.lox.TokenType.*;

class Scanner {
  private final String source;
  private final List<Token> tokens = new ArrayList<>();
  private int start = 0;
  private int current = 0;
  private int line = 1;

  Scanner(String source) {
    this.source = source;
  }

  List<Token> scanTokens(){
    while(!isAtEnd()){
      start = current;
      scanToken();
    }
    tokens.add(new Token(TokenType.EOF, "", null, line));
    return tokens;
  }

  void scanToken(){
    char c = advance();
    switch (c) {
      case '(': addToken(TokenType.LEFT_PAREN); break;
      case ')': addToken(TokenType.RIGHT_PAREN); break;
      case '[': addToken(TokenType.LEFT_BRACE); break;
      case ']': addToken(TokenType.RIGHT_BRACE); break;
      case ',': addToken(TokenType.COMMA); break;
      case '.': addToken(TokenType.DOT); break;
      case '-': addToken(TokenType.MINUS); break;
      case '+': addToken(TokenType.PLUS); break;
      case ';': addToken(TokenType.SEMICOLON); break;
      case '*': addToken(TokenType.STAR); break;
      case '!': addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG); break;
      case '=': addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL); break;
      case '<': addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS); break;
      case '>': addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER); break;
      case '/':
        if (match('/')){
          while (peek() != '\n' && !isAtEnd()) advance();
        } else {
          addToken(TokenType.SLASH);
        }
        break;
      case ' ':
      case '\r':
      case '\t':
        break;
      case '\n':
        line++;
        break;
      default:
        Lox.error(line, "Unexpected character.");
        break;
    }
  }

  char peek(){
    if (isAtEnd()) return '\0';
    return source.charAt(current);
  }

  boolean match(char checkChar){
    if (isAtEnd()) return false;
    if (source.charAt(current) != checkChar) return false;
    current++;
    return true;
  }

  void addToken(TokenType t){
    addToken(t, null);
  }

  void addToken(TokenType tt, Object literal){
    String text = source.substring(start, current);
    tokens.add(new Token(tt, text, literal, line));
  }

  char advance(){
    char c = source.charAt(current);
    current += 1;
    return c;
  }

  boolean isAtEnd(){
    return current >= source.length();
  }
}