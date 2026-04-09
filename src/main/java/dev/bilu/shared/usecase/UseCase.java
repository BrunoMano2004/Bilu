package dev.bilu.shared.usecase;

public interface UseCase<INPUT, OUTPUT> {
    OUTPUT execute(INPUT input);
}
