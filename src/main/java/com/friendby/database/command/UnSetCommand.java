package com.friendby.database.command;

import com.friendby.database.repository.StorageRepository;

import java.util.Map;

public class UnSetCommand extends CommandManipulation<Void> {

    private Map<String, Integer> memory;

    public UnSetCommand(String command) {
        super(command);
    }

    public UnSetCommand(String command, Map<String, Integer> memory) {
        super(command);
        this.memory = memory;
    }

    @Override
    public Void execute() {
        String key = command.split(" ")[1];
        if (memory != null) {
            memory.put("unset" + StorageRepository.get(key), StorageRepository.countByValue(StorageRepository.get(key)).intValue() - 1);
            memory.remove(key);
        } else
            StorageRepository.remove(key);
        return null;
    }
}
