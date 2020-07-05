package com.craftinginterpreters.lox;

public class RpnPrinter implements Expr.Visitor<String> {
  String print(Expr expr){
    return expr.accept(this);
  }

  @Override
  public String visitBinaryExpr(Expr.Binary expr){
    StringBuilder builder = new StringBuilder();
    builder.append(expr.left.accept(this));
    builder.append(" ");
    builder.append(expr.right.accept(this));
    builder.append(" ");
    builder.append(expr.operator.lexeme);
    builder.append(" ");
    return builder.toString();
  }

  @Override
  public String visitGroupingExpr(Expr.Grouping expr){
    return expr.expression.accept(this);
  }

  @Override
  public String visitLiteralExpr(Expr.Literal expr){
    if (expr.value == null) return "nil ";
    return expr.value.toString() + " ";
  }

  @Override
  public String visitUnaryExpr(Expr.Unary expr){
    StringBuilder builder = new StringBuilder();
    if(expr.operator.type == TokenType.MINUS){
      builder.append("0 ");
      builder.append(expr.right.accept(this));
      builder.append(" - ");
    }else{
      builder.append(expr.right.accept(this));
      builder.append(" ");
      builder.append(expr.operator.lexeme);
      builder.append(" ");
    }
    return builder.toString();
  }
}