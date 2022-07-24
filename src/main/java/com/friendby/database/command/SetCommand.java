package com.friendby.database.command;

import com.friendby.database.repository.StorageRepository;

import java.util.Map;

public class SetCommand extends CommandManipulation<Void> {

    private Map<String, Integer> memory;

    public SetCommand(String command) {
        super(command);
    }

    public SetCommand(String command, Map<String, Integer> memory) {
        super(command);
        this.memory = memory;
    }

    @Override
    public Void execute() {
        String key = command.split(" ")[1];
        Integer value = Integer.valueOf(command.split(" ")[2]);
        if (memory != null)
            memory.put(key, value);
        else
            StorageRepository.set(key, value);
        return null;
    }
}
