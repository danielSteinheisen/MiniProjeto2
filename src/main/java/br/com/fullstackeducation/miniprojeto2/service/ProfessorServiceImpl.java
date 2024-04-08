package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.ProfessorFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.exception.error.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.exception.error.ProfessorByNomeNotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.ProfessorRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl  implements ProfessorService {

    private final ProfessorRepository professorRepository;

    @Override
    public ProfessorEntity criarProfessor(ProfessorEntity professorNovo) {
        professorNovo.setId(null);
        log.info("Criando professor -> Salvar: \n{}\n", JsonUtil.objetoParaJson(professorNovo));
        ProfessorEntity professor = professorRepository.save(professorNovo);
        log.info("Criando professor -> Salvo com sucesso");
        log.debug("Criando professor -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(professorNovo));
        return professor;
    }

    @Override
    public List<ProfessorEntity> listarProfessores(ProfessorFiltro filtro) {
        List<ProfessorEntity> professores;

        if (StringUtils.hasText(filtro.getNome())) {
            log.info("Buscando todos os professores -> com nome ({})", filtro.getNome());
            professores = professorRepository.findByNomeContainingIgnoreCase(filtro.getNome());
        } else {
            log.info("Buscando todos os professores");
            professores = professorRepository.findAll();
        }

        log.info("Buscando todos os professores -> {} professores encontrados", professores.size());
        log.debug("Buscando todos os professores -> Registros encontrados:\n{}\n",
                JsonUtil.objetoParaJson(professores));
        return professores;
    }

    @Override
    public ProfessorEntity buscarProfessorPorId(Long id) {
        log.info("Buscando professor por ID: {}", id);
        Optional<ProfessorEntity> professor = professorRepository.findById(id);
        if (professor.isEmpty()) {
            log.error("Buscando professor por id {} -> NÃO Encontrado", id);
            return professorRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Professor não encontrado com o ID: " + id));
        }
        log.info("Buscando professor por id ({}) -> Encontrado", id);
        log.debug("Buscando professor por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objetoParaJson(professor.get()));
        return professor.get();
    }

    public ProfessorEntity buscarProfessorPorNome(String nome) {
        log.info("Buscando professor por Nome: {}", nome);
        Optional<ProfessorEntity> opt = professorRepository.findTop1ByNome(nome);

        if (opt.isEmpty()) {
            log.error("Buscando professor por nome {} -> NÃO Encontrado", nome);
            throw new ProfessorByNomeNotFoundException(nome);
        }

        log.info("Buscando professor por nome ({}) -> Encontrado", nome);
        log.debug("Buscando professor por nome ({}) -> Registro encontrado:\n{}\n", nome,
                JsonUtil.objetoParaJson(opt.get()));
        return opt.get();
    }

    @Override
    public ProfessorEntity atualizarProfessor(Long id, ProfessorEntity professor) {
        ProfessorEntity entity = buscarProfessorPorId(id);
        entity.setNome(professor.getNome());

        log.info("Alterando professor com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(entity));
        entity = professorRepository.save(entity);

        log.info("Alterando professor -> Salvo com sucesso");
        log.debug("Alterando professor -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(entity));
        return entity;
    }

    @Override
    public void excluirProfessor(Long id) {
        if (!professorRepository.existsById(id)) {
            log.info("Excluindo professor com id ({}) -> Excluindo", id);
            throw new NotFoundException("Professor não encontrada com o ID: " + id);
        }
        professorRepository.deleteById(id);
        log.info("Excluindo professor com id ({}) -> Excluído com sucesso", id);

    }
}
