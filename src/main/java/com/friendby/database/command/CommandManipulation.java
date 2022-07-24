package com.friendby.database.command;

public abstract class CommandManipulation<T> implements Command {

    protected final String command;

    CommandManipulation(String command){
        this.command = command;
    }

    @Override
    public CommandType type() {
        return CommandType.MANIPULATION;
    }

    abstract T execute();
}
