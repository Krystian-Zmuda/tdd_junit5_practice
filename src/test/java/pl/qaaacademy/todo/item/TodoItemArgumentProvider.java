package pl.qaaacademy.todo.item;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class TodoItemArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.arguments("Todo item 1", "Arguments provider description 1"),
                Arguments.arguments("Todo item 2", "Arguments provider description 2"),
                Arguments.arguments("Todo item 3", "Arguments provider description 3")
        );
    }
}
