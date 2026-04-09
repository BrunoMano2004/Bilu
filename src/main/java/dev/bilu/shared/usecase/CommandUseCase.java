package dev.bilu.shared.usecase;

public interface CommandUseCase<INPUT> {
    void execute(INPUT input);
}
